package CapaVista;

import CapaDomini.ControladorLogin;
import CapaDomini.ControladorPartida;
import CapaPersistencia.GestorHidato;
import CapaPersistencia.GestorPartida;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.List;

public class ComencarPartidaLauncher extends JDialog
{
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JRadioButton carregarPartidaRadioButton;
    private JRadioButton novaPartidaRadioButton;
    private JList llistaHidatosIPartides;
    private AbstractListModel<String> modelLlistaPartides;
    private AbstractListModel<String> modelLlistaHidatos;
    private ButtonGroup radioButtonGroup;

    public ComencarPartidaLauncher()
    {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        radioButtonGroup = new ButtonGroup();

        radioButtonGroup.add(carregarPartidaRadioButton);
        radioButtonGroup.add(novaPartidaRadioButton);
        carregarPartidaRadioButton.setActionCommand("carregar partida");
        novaPartidaRadioButton.setActionCommand("nova partida");

        carregaModel(1);
        carregaModel(2);

        llistaHidatosIPartides.setModel(modelLlistaHidatos);

        carregarPartidaRadioButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent actionEvent)
            {
                llistaHidatosIPartides.setModel(modelLlistaPartides);
            }
        });

        novaPartidaRadioButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent actionEvent)
            {
                llistaHidatosIPartides.setModel(modelLlistaHidatos);
            }
        });

        buttonOK.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent e)
            {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    public void carregaModel(int quinModel)
    {
        List<Integer> totesID;
        if (quinModel == 1) //Partida
        {
            totesID = GestorPartida.donaTotesID();
        }
        else
        {
            totesID = GestorHidato.donaTotesID();
        }

        DefaultListModel<String> listM = new DefaultListModel<>();
        for (Integer i : totesID)
        {
            listM.addElement(i.toString());
        }
        if (quinModel == 1)
        {
            modelLlistaPartides = listM;
        }
        else
        {
            modelLlistaHidatos = listM;
        }
    }

    private void onOK()
    {
        Integer idSeleccionada = Integer.valueOf((String) llistaHidatosIPartides.getSelectedValue());
        if (carregarPartidaRadioButton.isSelected())
        {
            ControladorPartida.carregarPartida(idSeleccionada); //todo verificar que un usuari nomes pugui carregar la seva partida
        }
        else if (novaPartidaRadioButton.isSelected())
        {
            ControladorPartida.novaPartida(ControladorLogin.getUsuariActual().getUniqID(), idSeleccionada);
        }
        else
        {
            throw new RuntimeException("Que vols que faci?");
        }
        ControladorPartida.jugaPartida();
        dispose();
    }

    private void onCancel()
    {
        // add your code here if necessary
        dispose();
    }

    public static void main(String[] args)
    {
        ComencarPartidaLauncher dialog = new ComencarPartidaLauncher();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }

    {
        // GUI initializer generated by IntelliJ IDEA GUI Designer
        // >>> IMPORTANT!! <<<
        // DO NOT EDIT OR ADD ANY CODE HERE!
      //  $$$setupUI$$$();
    }



    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$()
    {
        return contentPane;
    }
}
