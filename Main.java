import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.Hashtable;

class CircleAnimation extends JPanel {
    public int x = 50, y = 20;
    public int circleRadius = 20;
    public int vy = 10;

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        Ellipse2D.Double circle = new Ellipse2D.Double(x, y, circleRadius, circleRadius);
        repaint();
        g2.setColor(Color.green);
        g2.draw(circle);
        g2.fill(circle);
    }
}

class MyFrame extends JPanel implements ChangeListener, ActionListener {
    private Container c;
    private JLabel l1, l2,l3, r1, r2;
    CircleAnimation cc;
    private JTextField t1;
    private JLabel h1, h2, h3, h4, h5, h6, h7, h8;

    private JColorChooser c1;
    private JSlider s1, s2, s3;
    public JButton startAnimation, showval, ball_ground, reset_btn;
    public int p, count = 1, myfix = 20;
    public double dd;
    public float df;
    public Timer timer = new Timer(10, new runbhai());

    void MyFrame2() {
        JFrame f = new JFrame();
        f.setTitle("coeffient of rest.");

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        f.setSize(screenSize.width, screenSize.height);
        f.setDefaultCloseOperation(f.EXIT_ON_CLOSE);
        c = f.getContentPane();
        c.setLayout(null);

        l1 = new JLabel("Select Radius of ball");
        l1.setBounds(900, 50, 150, 20);
        c.add(l1);

        cc = new CircleAnimation();
        cc.setBounds(50, 20, 500, 681);
        c.add(cc);

        s1 = new JSlider(0, 200, 20);
        s1.setBounds(1100, 50, 120, 50);
        s1.setPaintTrack(true);
        s1.setPaintTicks(true);
        s1.setPaintLabels(true);
        s1.setMajorTickSpacing(50);
        s1.setMinorTickSpacing(5);
        c.add(s1);

        l2 = new JLabel("Select Coefficent of Restitution of ball");
        l2.setBounds(800, 180, 250, 20);
        c.add(l2);
        
        s2 = new JSlider(0, 10, 8);
        Hashtable labelTable = new Hashtable();
        labelTable.put((0), new JLabel("0.0"));
        labelTable.put((5), new JLabel("0.5"));
        labelTable.put((10), new JLabel("1.0"));
        s2.setLabelTable(labelTable);
        s2.setBounds(1100, 180, 120, 50);
        s2.setPaintTrack(true);
        s2.setPaintTicks(true);
        s2.setPaintLabels(true);
        s2.setMajorTickSpacing(5);
        s2.setMinorTickSpacing(1);
        c.add(s2);
        

        l3 = new JLabel("Height of ball");
        l3.setBounds(950, 250, 250, 20);
        c.add(l3);

        s3 = new JSlider(1, 101, 30);
        Hashtable labelTable2 = new Hashtable();
        labelTable2.put((1), new JLabel("1"));
        labelTable2.put((26), new JLabel("25"));
        labelTable2.put((51), new JLabel("50"));
        labelTable2.put((76), new JLabel("75"));
        labelTable2.put((101), new JLabel("100"));
        s3.setBounds(1100, 250, 120, 50);
        s3.setLabelTable(labelTable2);
        s3.setMajorTickSpacing(49);
        s3.setMinorTickSpacing(5);
        s3.setPaintLabels(true);
        s3.setPaintTicks(true);
        s3.setPaintTrack(true);
        c.add(s3);
        
        s1.addChangeListener(this);
        s2.addChangeListener(this);
        s3.addChangeListener(this);

        r1 = new JLabel("Output: ");
        r1.setBounds(900, 320, 150, 20);
        c.add(r1);

        startAnimation = new JButton("click to start Animation");
        startAnimation.setBounds(900, 360, 150, 20);
        c.add(startAnimation);
        startAnimation.addActionListener(this);

        reset_btn = new JButton("Reset");
        reset_btn.setBounds(900, 430, 150, 20);
        c.add(reset_btn);
        reset_btn.addActionListener(this);

        h1 = new JLabel("Height-1: ");
        h1.setBounds(900, 480, 100, 20);
        c.add(h1);
        h2 = new JLabel("Height-2: ");  `````                                                                                                                                                                                                                                                                                                                                                                                                                                   
        h2.setBounds(900, 510, 100, 20);
        c.add(h2);
        h3 = new JLabel("Height-3: ");
        h3.setBounds(900, 540, 100, 20);
        c.add(h3);
        h4 = new JLabel("Height-4: ");
        h4.setBounds(900, 570, 150, 20);
        c.add(h4);
        h5 = new JLabel("Height-5: ");
        h5.setBounds(1100, 480, 150, 20);
        c.add(h5);
        h6 = new JLabel("Height-6: ");
        h6.setBounds(1100, 510, 150, 20);
        c.add(h6);
        h7 = new JLabel("Height-7: ");
        h7.setBounds(1100, 540, 150, 20);
        c.add(h7);
        h8 = new JLabel("Height-8: ");
        h8.setBounds(1100, 570, 150, 20);
        c.add(h8);

        f.setVisible(true);
    }

    class runbhai implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (dd > 0) {
                cc.y = cc.y + (int) Math.ceil(dd);
            } 
            else {
                cc.y = cc.y + (int) Math.floor(dd);
            }
            repaint();
            if (cc.y + cc.circleRadius >= 680) {
                count++;
                cc.y -= dd;
                myfix = (int) (((double) (p + 20)) - ((double) p * Math.pow((s2.getValue() / 10.0), count)));
                dd = -1 * dd;
            }
            if (cc.y <= myfix) {
                dd *= -1;
            }
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startAnimation) {
            System.out.println("Ready TO GOO...");
            p = 650 - cc.y + cc.circleRadius;
            dd = 10 * Math.sqrt(2*10*s3.getValue());
            dd = p / dd;
            timer.start();
            for (int i = 2; i <= 16; i += 2) {
                df = s3.getValue() * (float) Math.pow((s2.getValue() / 10.0), i);
                if (i / 2 == 1) {
                    h1.setText("height-1 : " + df);
                }
                if (i / 2 == 2) {
                    h2.setText("height-2 : " + df);
                }
                if (i / 2 == 3) {
                    h3.setText("height-3 : " + df);
                }
                if (i / 2 == 4) {
                    h4.setText("height-4 : " + df);
                }
                if (i / 2 == 5) {
                    h5.setText("height-5 : " + df);
                }
                if (i / 2 == 6) {
                    h6.setText("height-6 : " + df);
                }
                if (i / 2 == 7) {
                    h7.setText("height-7 : " + df);
                }
                if (i / 2 == 8) {
                    h8.setText("height-8 : " + df);
                }
            }
        }
        if (e.getSource() == reset_btn) {
            System.out.println("reset");
            cc.circleRadius = 20;
            timer.restart();
            timer.stop();
            cc.x = 50;
            cc.y = 20;
            s2.setValue(8);
            s1.setValue(20);
            s3.setValue(25);
            count = 1;
            dd = 1;
            myfix = 20;
        }
    }

    public void stateChanged(ChangeEvent e) {
        if (e.getSource() == s1) {
            cc.circleRadius = s1.getValue();
            r1.setText("value is  " + s1.getValue());
        }
        if (e.getSource() == s2) {
            r1.setText("value is  " + ((double) s2.getValue() / 10));

        }
        if (e.getSource() == s3) {
            r1.setText("value is  " + s3.getValue());
        }
    }

}

class Main {

    public static void main(String args[]) {
        MyFrame frame = new MyFrame();
        frame.MyFrame2();
    }
}