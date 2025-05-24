package org.example;

import javax.swing.*;
import java.awt.*;

public class ResultGui2 extends JFrame {
    private ResultGui2 resultGui2;
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



    EmpoTable model5;
    JTable table5;
    JScrollPane jScrollPane5;

    JPanel panel;

    JButton deleteButton;

    JPanel TablePanel;

    public ResultGui2(String title){
        this.setTitle(title);
        model = new Tablee();
        table = new JTable(model);
        jScrollPane = new JScrollPane(table);
        jScrollPane = createScrollPaneWithStyle(table);

        model2 = new TabDeduc();
        table2 = new JTable(model2);
        jScrollPane2 = new JScrollPane(table2);
        jScrollPane2 = createScrollPaneWithStyle(table2);

        model3 = new TabTotal();
        table3 = new JTable(model3);
        jScrollPane3 = new JScrollPane(table3);
        jScrollPane3 = createScrollPaneWithStyle(table3);



        model5 = new EmpoTable();
        table5 = new JTable(model5);
        jScrollPane5 = new JScrollPane(table5);
        jScrollPane5 = createScrollPaneWithStyle(table5);

        setLayout(new BorderLayout());

        add(jScrollPane5, BorderLayout.NORTH);
        add(jScrollPane, BorderLayout.CENTER);
        add(jScrollPane2, BorderLayout.EAST);
        add(jScrollPane3, BorderLayout.SOUTH);


        deleteButton = new JButton("Delete");
        panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panel.setBackground(new Color(0, 0, 64));

        panel.add(deleteButton);

        TablePanel = new JPanel();

        TablePanel.add(jScrollPane5);
        TablePanel.add(jScrollPane);
        TablePanel.add(jScrollPane2);
        TablePanel.add(jScrollPane3);


        layout = new GridBagLayout();
        container = this.getContentPane();
        container.setLayout(layout);

        //adding bg
        container.setBackground(new Color(0, 0, 64));

//        addtoCon(jScrollPane,0,0,1,1);
//        addtoCon(jScrollPane2,1,0,1,1);
//        addtoCon(jScrollPane3,2,0,1,1);

          addtoCon(TablePanel,0,0,1,1);

        //addtoCon(jScrollPane4,3,0,1,1);

        addtoCon(panel,0,1,1,1);

        this.setVisible(true);
        this.pack();
        //stop resize
        this.setResizable(false);
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


    //Adjust table size
    private JScrollPane createScrollPaneWithStyle(JTable table) {
        table.setFont(new Font("Arial", Font.PLAIN, 12));
        table.setRowHeight(20);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(400, 600));
        return scrollPane;
    }

}