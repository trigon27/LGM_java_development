import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class CurrencyConverter implements ActionListener {

    String[] Countries={"India","US","Canada","France"};
    JComboBox fromBox;
    JComboBox ToBox;
    TextField ResultText;
    TextField AmountText;
    CurrencyConverter()
    {
        JFrame frame =new JFrame("currency converter");
        //hading
        JPanel topPanel = new JPanel();
        topPanel.setBounds(18,10,550,70);
        topPanel.setBackground(new Color(176,196,222));

        Font big=new Font("times new roman",Font.BOLD,35);
        Label heading=new Label("currency converter");
        heading.setForeground(new Color(32,32,32));

        heading.setBounds(200,15,100,50);
        heading.setSize(100,50);
        heading.setFont(big);
        topPanel.add(heading);
        //main panel
        JPanel body=new JPanel();
        //labels
        JLabel from=new JLabel("From :");
        Font medium =new Font("times new roman",Font.PLAIN,18);
        from.setBounds(20,100,60,18);
        from.setForeground(Color.white);
        from.setFont(medium);

        JLabel to=new JLabel("To :");
        to.setBounds(300,100,30,18);
        to.setForeground(Color.white);
        to.setFont(medium);

        //combo box

        fromBox =new JComboBox(Countries);
        fromBox.setBounds(90,100,120,20);
        fromBox.setForeground(Color.black);
        fromBox.setBackground(new Color(225,220,225));
        fromBox.setFocusable(false);

        ToBox =new JComboBox(Countries);
        ToBox.setBounds(350,100,120,20);
        ToBox.setForeground(Color.black);
        ToBox.setBackground(new Color(225,220,225));
        ToBox.setFocusable(false);

        //Amount
        JLabel Amount=new JLabel("Amount : ");
        Amount.setBounds(20,20,80,25);
        Amount.setFont(medium);
        Amount.setForeground(Color.white);

        AmountText=new TextField();
        AmountText.setBounds(120,20,300,25);
        AmountText.setFont(medium);

        //result
        JLabel result=new JLabel("Result : ");
        result.setBounds(20,200,80,25);
        result.setFont(medium);
        result.setForeground(Color.white);

        ResultText=new TextField();
        ResultText.setBounds(100,200,150,25);
        ResultText.setFont(medium);

        //convert button
        JButton converter =new JButton("Convert");
        converter.setBounds(190,270,150,40);
        converter.setFont(medium);
        converter.setBackground(new Color(176,196,222));
        converter.setFocusable(false);
        converter.addActionListener(this);


        body.setBounds(18,80,550,350);
        body.setBackground(new Color(105,105,105));
        body.setLayout(null);
        body.add(from);
        body.add(fromBox);
        body.add(to);
        body.add(ToBox);
        body.add(Amount);
        body.add(AmountText);
        body.add(result);
        body.add(ResultText);
        body.add(converter);


        frame.setLayout(null);
        frame.setLocation(400,250);
        frame.setSize(600,500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(topPanel);
        frame.add(body);
        frame.setVisible(true);

    }
    @Override
    public void actionPerformed(ActionEvent e)
    {
       double  Amountvalue=Double.parseDouble(AmountText.getText());
       double resultvalue=0;
      String fromvalue=fromBox.getSelectedItem().toString();
      String Tovalue=ToBox.getSelectedItem().toString();

        switch (fromvalue)
        {
            case "India":
                switch (Tovalue)
                {
                    case "India":
                        resultvalue=Amountvalue;
                        break;
                    case "US":
                        resultvalue=Amountvalue/82.5875;
                        break;
                    case "Canada":
                        resultvalue=Amountvalue/60.19;
                        break;
                    case "France":
                        resultvalue=Amountvalue/88.2778;
                        break;
                }
                break;
            case "US":
                switch (Tovalue)
                {
                    case "India":
                        resultvalue=Amountvalue*82.5875;
                        break;
                    case "US":
                        resultvalue=Amountvalue;
                        break;
                    case "Canada":
                        resultvalue=Amountvalue*1.34234;
                        break;
                    case "France":
                        resultvalue=Amountvalue*0.9352;
                        break;
                }
                break;

            case "Canada":
                switch (Tovalue)
                {
                    case "India":
                        resultvalue=Amountvalue*61.5033;
                        break;
                    case "US":
                        resultvalue=Amountvalue*0.7445;
                        break;
                    case "Canada":
                        resultvalue=Amountvalue;
                        break;
                    case "France":
                        resultvalue=Amountvalue*0.6966;
                        break;
                }
                break;
            case "France":
                switch (Tovalue)
                {
                    case "India":
                        resultvalue=Amountvalue*88.2778;
                        break;
                    case "US":
                        resultvalue=Amountvalue*1.0689;
                        break;
                    case "Canada":
                        resultvalue=Amountvalue*1.4358;
                        break;
                    case "France":
                        resultvalue=Amountvalue;
                        break;
                }
                break;
            default:
                resultvalue=0;
        }

        ResultText.setText(String.valueOf(resultvalue));
        ResultText.setEditable(false);
    }
    static public void main(String[] args)
    {

        new CurrencyConverter();
    }

}