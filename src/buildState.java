import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class buildState extends JLayeredPane {
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    private static final int GRID_ROWS = 13;
    private static final int GRID_COLS = 3;
    private static final int GAP = 3;
    private static final Dimension LAYERED_PANE_SIZE = new Dimension(WIDTH, HEIGHT);
    private static final Dimension LABEL_SIZE = new Dimension(80, 40);
    private GridLayout gridlayout = new GridLayout(GRID_ROWS, GRID_COLS, GAP, GAP);
    private JPanel backingPanel = new JPanel(gridlayout);
    private JPanel[][] panelGrid = new JPanel[GRID_ROWS][GRID_COLS];
    private JLabel aLabel = new JLabel("A", SwingConstants.CENTER);
    private JLabel bLabel = new JLabel("B", SwingConstants.CENTER);
    private JLabel cLabel = new JLabel("C", SwingConstants.CENTER);
    private JLabel dLabel = new JLabel("D", SwingConstants.CENTER);
    private JLabel eLabel = new JLabel("E", SwingConstants.CENTER);
    private JLabel fLabel = new JLabel("F", SwingConstants.CENTER);
    private JLabel gLabel = new JLabel("G", SwingConstants.CENTER);
    private JLabel hLabel = new JLabel("H", SwingConstants.CENTER);
    private JLabel iLabel = new JLabel("I", SwingConstants.CENTER);
    private JLabel jLabel = new JLabel("J", SwingConstants.CENTER);
    private JLabel kLabel = new JLabel("K", SwingConstants.CENTER);
    private JLabel mLabel = new JLabel("M", SwingConstants.CENTER);
    private JLabel nLabel = new JLabel("N", SwingConstants.CENTER);

    public buildState() {
        backingPanel.setSize(LAYERED_PANE_SIZE);
        backingPanel.setLocation(GAP, GAP);
        backingPanel.setBackground(Color.black);
        for (int row = 0; row < GRID_ROWS; row++) {
            for (int col = 0; col < GRID_COLS; col++) {
                panelGrid[row][col] = new JPanel(new GridBagLayout());
                backingPanel.add(panelGrid[row][col]);
            }
        }

        labelPrep(aLabel);
        labelPrep(bLabel);
        labelPrep(cLabel);
        labelPrep(dLabel);
        labelPrep(eLabel);
        labelPrep(fLabel);
        labelPrep(gLabel);
        labelPrep(hLabel);
        labelPrep(iLabel);
        labelPrep(jLabel);
        labelPrep(kLabel);
        labelPrep(mLabel);
        labelPrep(nLabel);

        panelGrid[0][0].add(aLabel);
        panelGrid[1][0].add(bLabel);
        panelGrid[2][0].add(cLabel);
        panelGrid[3][0].add(dLabel);
        panelGrid[4][0].add(eLabel);
        panelGrid[5][0].add(fLabel);
        panelGrid[6][0].add(gLabel);
        panelGrid[7][0].add(hLabel);
        panelGrid[8][0].add(iLabel);
        panelGrid[9][0].add(jLabel);
        panelGrid[10][0].add(kLabel);
        panelGrid[11][0].add(mLabel);
        panelGrid[12][0].add(nLabel);

        backingPanel.setBorder(BorderFactory.createEmptyBorder(GAP, GAP, GAP, GAP));
        setPreferredSize(LAYERED_PANE_SIZE);
        add(backingPanel, JLayeredPane.DEFAULT_LAYER);
        MyMouseAdapter myMouseAdapter = new MyMouseAdapter();
        addMouseListener(myMouseAdapter);
        addMouseMotionListener(myMouseAdapter);
    }

    private void labelPrep(JLabel Label){
        Label.setOpaque(true);
        Label.setBackground(Color.ORANGE);
        Label.setPreferredSize(LABEL_SIZE);
    }
    private class MyMouseAdapter extends MouseAdapter {
        private JLabel dragLabel = null;
        private int dragLabelWidthDiv2;
        private int dragLabelHeightDiv2;
        private JPanel clickedPanel = null;

        @Override
        public void mousePressed(MouseEvent me) {
            clickedPanel = (JPanel) backingPanel.getComponentAt(me.getPoint());
            Component[] components = clickedPanel.getComponents();
            if (components.length == 0) {
                return;
            }
            // if we click on jpanel that holds a jlabel
            if (components[0] instanceof JLabel) {

                // remove label from panel
                dragLabel = (JLabel) components[0];
                clickedPanel.remove(dragLabel);
                clickedPanel.revalidate();
                clickedPanel.repaint();

                dragLabelWidthDiv2 = dragLabel.getWidth() / 2;
                dragLabelHeightDiv2 = dragLabel.getHeight() / 2;

                int x = me.getPoint().x - dragLabelWidthDiv2;
                int y = me.getPoint().y - dragLabelHeightDiv2;
                dragLabel.setLocation(x, y);
                add(dragLabel, JLayeredPane.DRAG_LAYER);
                repaint();
            }
        }

        @Override
        public void mouseDragged(MouseEvent me) {
            if (dragLabel == null) {
                return;
            }
            int x = me.getPoint().x - dragLabelWidthDiv2;
            int y = me.getPoint().y - dragLabelHeightDiv2;
            dragLabel.setLocation(x, y);
            repaint();
        }

        @Override
        public void mouseReleased(MouseEvent me) {
            if (dragLabel == null) {
                return;
            }
            remove(dragLabel); // remove dragLabel for drag layer of JLayeredPane
            JPanel droppedPanel = (JPanel) backingPanel.getComponentAt(me.getPoint());
            if (droppedPanel == null) {
                // if off the grid, return label to home
                clickedPanel.add(dragLabel);
                clickedPanel.revalidate();
            } else {
                int r = -1;
                int c = -1;
                searchPanelGrid: for (int row = 0; row < panelGrid.length; row++) {
                    for (int col = 0; col < panelGrid[row].length; col++) {
                        if (panelGrid[row][col] == droppedPanel) {
                            r = row;
                            c = col;
                            break searchPanelGrid;
                        }
                    }
                }

                if (r == -1 || c == -1) {
                    // if off the grid, return label to home
                    clickedPanel.add(dragLabel);
                    clickedPanel.revalidate();
                } else {
                    droppedPanel.add(dragLabel);
                    droppedPanel.revalidate();
                }
            }

            repaint();
            dragLabel = null;
        }
    }

    private static void createAndShowUI() {
        JFrame frame = new JFrame("Build State");
        frame.getContentPane().add(new buildState());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                createAndShowUI();
            }
        });
    }
}