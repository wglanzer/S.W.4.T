package de.swat.MapCreator.gui.dialogs;

import de.swat.FileUtils;
import de.swat.IFileStructure;
import de.swat.ServerSearcher;
import de.swat.clientserverintercom.ICSInterConstants;
import de.swat.fileTransfer.FileTransferClient;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

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
  private JList<File> mapList;
  private JButton refreshServerListButton = new JButton("Refresh");
  private JButton sendButton = new JButton("Send...");
  private boolean findServerThreadRunning = false;

  public SendMapDialog()
  {
    setLayout(new BorderLayout());
    setSize(new Dimension(900, 500));
    setLocationRelativeTo(null);
    setDefaultCloseOperation(DISPOSE_ON_CLOSE);

    _configureServerList();
    _configureMapList();

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
  }

  /**
   * Füllt die ServerList
   */
  private void _configureServerList()
  {
    serverList = new JList<>();
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
   * Layoutet den Dialog und fügt alle Komponenten zu einem JPanel, das zurückgegeben wird, hinzu
   */
  private JPanel _createRootPanel()
  {
    int border = 4;
    
    JPanel root = new JPanel();
    root.setLayout(new BorderLayout());
    root.setBorder(BorderFactory.createEmptyBorder(border, border, border, border));

    root.add(description, BorderLayout.NORTH);

    JPanel contentLeft = new JPanel();
    contentLeft.setBorder(new EmptyBorder(border, 0, 0, border));
    contentLeft.setLayout(new BorderLayout());
    contentLeft.setPreferredSize(new Dimension(275, 0));
    serverList.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(0, 0, border, 0), BorderFactory.createLineBorder(Color.BLACK)));
    contentLeft.add(serverList, BorderLayout.CENTER);
    contentLeft.add(refreshServerListButton, BorderLayout.SOUTH);
    root.add(contentLeft, BorderLayout.WEST);

    JPanel contentCenter = new JPanel();
    contentCenter.setBorder(BorderFactory.createEmptyBorder(border, 0, 0, 0));
    contentCenter.setLayout(new BorderLayout());
    mapList.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(0, 0, border, 0), BorderFactory.createLineBorder(Color.BLACK)));
    contentCenter.add(mapList, BorderLayout.CENTER);
    JPanel sendButtonPanel = new JPanel();
    sendButtonPanel.setLayout(new BorderLayout());
    sendButtonPanel.add(sendButton, BorderLayout.EAST);
    contentCenter.add(sendButtonPanel, BorderLayout.SOUTH);
    root.add(contentCenter, BorderLayout.CENTER);

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

        serverList.setModel(new AbstractListModel<String>()
        {
          public int getSize()
          {
            return result.size();
          }

          public String getElementAt(int i)
          {
            return result.get(i);
          }
        });

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
