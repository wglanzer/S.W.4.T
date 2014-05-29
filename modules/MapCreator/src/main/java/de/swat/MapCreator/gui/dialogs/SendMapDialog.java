package de.swat.MapCreator.gui.dialogs;

import de.swat.FileUtils;
import de.swat.IFileStructure;
import de.swat.ServerSearcher;
import de.swat.clientserverintercom.ICSInterConstants;
import de.swat.constants.IVersion;
import de.swat.fileTransfer.FileTransferClient;
import de.swat.utils.StringUtil;
import info.clearthought.layout.TableLayout;
import sun.net.util.IPAddressUtil;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.io.File;
import java.text.MessageFormat;
import java.util.*;
import java.util.List;

/**
 * Dialog der dazu verwendet wird, um fertige Maps an einen Client zu versenden.
 *
 * @author W.Glanzer, 03.05.2014.
 */
public class SendMapDialog extends JDialog
{

  private JLabel description = new JLabel("<html>Select the device you want to transfer data to from the list below left, " +
      "you need to make sure that the Transfer-Mode of your Android device is enabled. " +
      "If your device doesn't appear on the list, you may have to click \"Refresh\". " +
      "After selecting your device, select the map(s) you want to transfer and click the \"Transfer\" - button.</html>");
  private JList<String> serverList;
  private DefaultListModel<String> serverListModel;
  private Set<String> extraIPs = new HashSet<>();
  private JList<File> mapList;
  private JButton refreshServerListButton = new JButton("Refresh");
  private JButton addIPButton = new JButton("Add");
  private JButton sendButton = new JButton("Send");
  private JTextField addIPTextField = new JTextField();
  private boolean findServerThreadRunning = false;

  public SendMapDialog()
  {
    setLayout(new BorderLayout());
    setSize(new Dimension(900, 500));
    setLocationRelativeTo(null);
    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    setTitle(MessageFormat.format(IVersion.SWAT_TITLE_TEMPLATE, "Map Transfer"));

    _configureServerList();
    _configureMapList();
    _configureAddIP();

    _SendButtonSelectionListener listener = new _SendButtonSelectionListener();
    serverList.addListSelectionListener(listener);
    mapList.addListSelectionListener(listener);

    sendButton.addActionListener(new _SendButtonActionListener());
    sendButton.setEnabled(false);

    add(_createRootPanel(), BorderLayout.CENTER);
  }

  /**
   * Füllt die MapList
   */
  private void _configureMapList()
  {
    Vector<File> mapVector = new Vector<>();

    File maps = new File(FileUtils.getFilesDir().getPath() + IFileStructure.MAPS + "/test");
    if(!maps.exists())
      maps.mkdir();

    if(maps.exists() && maps.isDirectory())
    {
      File[] files = maps.listFiles();
      if(files != null)
        mapVector.addAll(Arrays.asList(files));
    }

    mapList = new JList<>(mapVector);
    mapList.setBorder(BorderFactory.createLineBorder(Color.BLACK));
  }

  /**
   * Füllt die ServerList
   */
  private void _configureServerList()
  {
    serverList = new JList<>();
    serverList.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    serverList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    new Thread(new _FillServerListThread()).start();
    refreshServerListButton.addActionListener(new ActionListener()
    {
      @Override
      public void actionPerformed(ActionEvent e)
      {
        new Thread(new _FillServerListThread()).start();
      }
    });
  }

  /**
   * Initialisiert die "AddIP"-Funktion
   */
  private void _configureAddIP()
  {
    addIPTextField = new JTextField();
    addIPTextField.addFocusListener(new FocusAdapter()
    {
      @Override
      public void focusGained(FocusEvent e)
      {
        SwingUtilities.invokeLater(new Runnable()
        {
          @Override
          public void run()
          {
            SwingUtilities.getRootPane(addIPTextField).setDefaultButton(addIPButton);
          }
        });
      }

      @Override
      public void focusLost(FocusEvent e)
      {
        SwingUtilities.invokeLater(new Runnable()
        {
          @Override
          public void run()
          {
            SwingUtilities.getRootPane(addIPTextField).setDefaultButton(sendButton);
            revalidate();
            repaint();
          }
        });
      }
    });

    addIPTextField.getDocument().addDocumentListener(new DocumentListener()
    {
      @Override
      public void insertUpdate(DocumentEvent e)
      {
        _setEnabled();
      }

      @Override
      public void removeUpdate(DocumentEvent e)
      {
        _setEnabled();
      }

      @Override
      public void changedUpdate(DocumentEvent e)
      {
        _setEnabled();
      }

      private void _setEnabled()
      {
        String text = addIPTextField.getText();
        addIPButton.setEnabled(IPAddressUtil.isIPv4LiteralAddress(text) && StringUtil.countNumber(text, ".") == 3);
      }
    });

    addIPButton.setEnabled(false);
    addIPButton.addActionListener(new ActionListener()
    {
      @Override
      public void actionPerformed(ActionEvent e)
      {
        String text = addIPTextField.getText();
        if(!serverListModel.contains(text) && !extraIPs.contains(text))
        {
          extraIPs.add(text);
          serverListModel.addElement(text);
          addIPTextField.setText("");
        }
      }
    });
  }

  /**
   * Layoutet den Dialog und fügt alle Komponenten zu einem JPanel, das zurückgegeben wird, hinzu
   */
  private JPanel _createRootPanel()
  {
    int border = 5;
    JPanel root = new JPanel();
    double[][] layout = new double[][]{{border, 147, border, 64, border, 64, border, TableLayout.FILL, border},
        {border, TableLayout.PREFERRED, border, TableLayout.FILL, border, TableLayout.PREFERRED, border}};
    root.setLayout(new TableLayout(layout));

    root.add(description, "1, 1, 7, 1");
    root.add(serverList, "1, 3, 5, 3");
    root.add(mapList, "7, 3");
    root.add(addIPTextField, "1, 5");
    root.add(addIPButton, "3, 5");
    root.add(refreshServerListButton, "5, 5");

    JPanel sendButtonPanel = new JPanel(new BorderLayout());
    sendButtonPanel.add(sendButton, BorderLayout.EAST);

    root.add(sendButtonPanel, "7, 5");

    root.setOpaque(true);
    return root;
  }

  /**
   * Listener, der den sendButton updated
   */
  private class _SendButtonSelectionListener implements ListSelectionListener
  {
    @Override
    public void valueChanged(ListSelectionEvent e)
    {
      sendButton.setEnabled(!serverList.getSelectedValuesList().isEmpty() && !mapList.getSelectedValuesList().isEmpty());
    }
  }

  /**
   * Thread, der die serverListe füllt
   */
  private class _FillServerListThread implements Runnable
  {
    @Override
    public void run()
    {
      if(!findServerThreadRunning)
      {
        findServerThreadRunning = true;

        serverList.setEnabled(false);
        final Vector<String> result = ServerSearcher.search(null, ICSInterConstants.FILETRANSFERPORT);

        serverListModel = new DefaultListModel<>();

        for(String currResult : result)
          serverListModel.addElement(currResult);

        for(String currIP : extraIPs)
          serverListModel.addElement(currIP);


        serverList.setModel(serverListModel);
        serverList.setEnabled(true);
        findServerThreadRunning = false;
      }
    }
  }

  /**
   * ActionListener für den sendButton.
   * Hier werden Daten von Client zum Server geschickt
   */
  private class _SendButtonActionListener implements ActionListener
  {
    @Override
    public void actionPerformed(ActionEvent e)
    {
      String connectToIP = serverList.getSelectedValue();
      List<File> mapsToSend = mapList.getSelectedValuesList();

      FileTransferClient ftClient = new FileTransferClient(connectToIP);
      for(File currFile : mapsToSend)
        ftClient.sendServerMessage(currFile);
    }
  }
}
