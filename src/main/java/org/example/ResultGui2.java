package org.example;

import javax.swing.*;
import java.awt.*;

public class ResultGui2 extends JFrame {
    GridBagLayout layout;
    Container container;

    Tablee model;
    JTable table;
    JScrollPane jScrollPane;

    TabDeduc model2;
    JTable table2;
    JScrollPane jScrollPane2;

    TabTotal model3;
    JTable table3;
    JScrollPane jScrollPane3;

    JPanel panel;

    JButton deleteButton;

    public ResultGui2(String title){
        this.setTitle(title);
        model = new Tablee();
        table = new JTable(model);
        jScrollPane = new JScrollPane(table);

        model2 = new TabDeduc();
        table2 = new JTable(model2);
        jScrollPane2 = new JScrollPane(table2);

        model3 = new TabTotal();
        table3 = new JTable(model3);
        jScrollPane3 = new JScrollPane(table3);

        deleteButton = new JButton("Delete");
        panel = new JPanel();
        panel.add(deleteButton);


        layout = new GridBagLayout();
        container = this.getContentPane();
        container.setLayout(layout);

        addtoCon(jScrollPane,0,0,1,1);
        addtoCon(jScrollPane2,1,0,1,1);
        addtoCon(jScrollPane3,2,0,1,1);
        addtoCon(panel,1,1,1,1);

        this.setVisible(true);
        this.pack();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void addtoCon(Container e, int x, int y, int wx, int wy){
        GridBagConstraints gridBagConstraints = new GridBagConstraints();

        gridBagConstraints.gridx=x;
        gridBagConstraints.gridy=y;
        gridBagConstraints.gridwidth=wx;
        gridBagConstraints.gridheight=wy;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.insets = new Insets(5,5,5,5);

        container.add(e,gridBagConstraints);
    }
}