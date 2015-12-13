package CapaVista;

import CapaDomini.Casella;

import javax.swing.*;
import java.awt.*;

/**
 * Created by daniel on 16/11/15.
 */
public class CasellaLabel extends JLabel
{
    private Casella continguts;
    private static final Color ACTIVE_COLOR = Color.YELLOW;
    private static final Color PASSIVE_COLOR = Color.BLUE;

    public CasellaLabel()
    {
        super();
        continguts = new Casella();
        setOpaque(true);
        setMinimumSize(new Dimension(40,40));
        setHorizontalAlignment(SwingConstants.CENTER);
        setHighlight(false);
    }

    public CasellaLabel(Casella c)
    {
        super();
        continguts = new Casella(c);
        setCasellaElem(c.elem);
        setOpaque(true);
        setMinimumSize(new Dimension(40,40));
        setHorizontalAlignment(SwingConstants.CENTER);
        setHighlight(false);
    }

    public int getCasellaX()
    {
        return continguts.getX();
    }

    public int getCasellaY()
    {
        return continguts.getY();
    }

    public int getCasellaElem()
    {
        return continguts.getElem();
    }

    public void setCasellaElem(int elem)
    {
        continguts.setElem(elem);
        String text;
        if (elem == Casella.BUIT)
            text = " ";
        else if (elem == Casella.FORAT)
            text = "X";
        else
            text = Integer.toString(elem);
        setText(text);
    }

    public void setHighlight(boolean b)
    {
        Color toSet = b ? ACTIVE_COLOR : PASSIVE_COLOR;
        setBackground(toSet);
    }
}
