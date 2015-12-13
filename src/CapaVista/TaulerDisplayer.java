package CapaVista;

import CapaDomini.Casella;
import CapaDomini.Tauler;
import CapaDomini.TaulerDisplayerCallbacks;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Es la classe que et permet veure e interactuar amb l'Hidato
 * @author Dani Armengod
 */
public class TaulerDisplayer extends JPanel
{
    /**
     * todo:
     * implementar espacios entre casillas
     * resuleto GridLayout.set{H,V}gap(int);
     * implementar highlighting
     * resuelto JLabel.setForeground(Color.XXX | null)
     * implementar click-edit
     */
    private CasellaLabel[][] numerosAlTauler;
    private Casella focus;
    private Casella oldFocus;
    private int numeroInputBuffer = 0;
    private TaulerDisplayerCallbacks callbackMethods;

    public void setCallbackMethods(TaulerDisplayerCallbacks c)
    {
        this.callbackMethods = c;
    }

    public void setTauler(Tauler t)
    {
        int tamany = t.getTamany();
        GridLayout g = new GridLayout(tamany, tamany);
        setLayout(g);
        numerosAlTauler = new CasellaLabel[tamany][tamany];
        for (int i = 0; i < tamany; ++i)
        {
            for (int j = 0; j < tamany; ++j)
            {
                numerosAlTauler[i][j] = new CasellaLabel(t.getCasella(i,j));
                numerosAlTauler[i][j].setFont(numerosAlTauler[i][j].getFont().deriveFont(32.0f));
                add(numerosAlTauler[i][j]);
            }
        }
        focus = new Casella(tamany / 2, tamany / 2);
        oldFocus = focus;
        setHighlightOnFocus();
        g.setHgap(5);
        g.setVgap(5);
    }

    public TaulerDisplayer()
    {
        setFocusable(true);
        addKeyListener(new KeyListener()
        {
            @Override
            public void keyTyped(KeyEvent keyEvent)
            {
                char caracterIntroduit = keyEvent.getKeyChar();
                if ('0' <= caracterIntroduit && caracterIntroduit <= '9')
                {
                    numeroInputBuffer *= 10;
                    numeroInputBuffer += caracterIntroduit - '0';

                }
            }

            @Override
            public void keyPressed(KeyEvent keyEvent)
            {
                Casella casellaASumar = null;
                Casella aModificar = null;
                switch (keyEvent.getKeyCode())
                {
                    case KeyEvent.VK_LEFT:
                        casellaASumar = new Casella(0, -1);
                        break;
                    case KeyEvent.VK_RIGHT:
                        casellaASumar = new Casella(0, 1);
                        break;
                    case KeyEvent.VK_UP:
                        casellaASumar = new Casella(-1, 0);
                        break;
                    case KeyEvent.VK_DOWN:
                        casellaASumar = new Casella(1,0);
                        break;
                    case KeyEvent.VK_SHIFT:
                    case KeyEvent.VK_ENTER:
                        aModificar = new Casella(focus);
                        aModificar.setElem(numeroInputBuffer);
                        break;
                    case KeyEvent.VK_BACK_SPACE:
                        aModificar = new Casella(focus);
                        aModificar.setElem(Casella.BUIT);
                        break;
                    case KeyEvent.VK_X:
                        aModificar = new Casella(focus);
                        aModificar.setElem(Casella.FORAT);
                        break;
                }
                if (casellaASumar != null)
                {
                    numeroInputBuffer = 0;
                    focus = focus.sumaAmbCheck(casellaASumar, 0, getTamany() - 1);
                    setHighlightOnFocus();
                }
                if (aModificar != null)
                {
                    numeroInputBuffer = 0;
                    if (callbackMethods.casellaModificada(aModificar))
                    {
                        focus = aModificar;
                        setCasella(focus);
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent keyEvent)
            {

            }
        });
    }

    public void setHighlightOnFocus()
    {
        numerosAlTauler[oldFocus.x][oldFocus.y].setHighlight(false);
        numerosAlTauler[focus.x][focus.y].setHighlight(true);
        oldFocus = focus;
    }

    public int getTamany()
    {
        return numerosAlTauler.length;
    }

    public void setCasella(Casella c)
    {
        numerosAlTauler[c.x][c.y].setCasellaElem(c.elem);
    }

//    public static void main(String[] args)
//    {
//        JFrame f = new JFrame();
//        TaulerDisplayer t = new TaulerDisplayer(10);
//        f.setContentPane(t);
//        for (int i = 0; i < 10; ++i)
//            for (int j = 0; j < 10; ++j)
//                t.setCasella(new Casella(i, j, i + 1));
//        f.pack();
//        f.setVisible(true);
//    }

}
