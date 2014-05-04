package de.swat.MapCreator.gui.dialogs;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.Vector;

/**
 * Dialog der dazu verwendet wird, um fertige Maps an einen Client zu versenden.
 *
 * @author W.Glanzer, 03.05.2014.
 */
public class SendMapDialog extends JDialog
{

  private JLabel description = new JLabel("Select the device you want to transfer data to from the list below left, " +
      "you need to make sure that the Transfer-Mode of your Android device is enabled. " +
      "If your device doesn't appear on the list, you may have to click \"Refresh\". " +
      "After selecting your device, select the map(s) you want to transfer and click the \"Transfer\" - button.");
  private JList serverList;
  private JList mapList;
  private JButton refreshServerListButton = new JButton("Refresh");
  private JButton sendButton = new JButton("Send...");

  public SendMapDialog()
  {
    setLayout(new BorderLayout());
    setSize(new Dimension(900, 500));
    setLocationRelativeTo(null);
    setDefaultCloseOperation(DISPOSE_ON_CLOSE);

    _fillLists();
    add(_createRootPanel(), BorderLayout.CENTER);
  }

  /**
   * Füllt die Map- und die ServerList
   */
  private void _fillLists()
  {
    serverList = new JList<>(_getServers());
    serverList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

    mapList = new JList<>(new String[]{"Hallo", "Welt"});
  }

  /**
   * @return Gibt alle Server im Netzwerk zurück
   */
  private Vector<String> _getServers()
  {
    Vector<String> returnList = new Vector<>();

    return returnList;
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
}
