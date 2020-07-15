/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package notepad;
import com.sun.javafx.binding.StringFormatter;
import com.sun.xml.internal.ws.util.StringUtils;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.event.*;
import java.awt.print.PageFormat;
import java.awt.print.PrinterJob;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.*;
import javax.swing.*;
import javax.swing.JMenu;
import javax.swing.JMenuBar;

/**
 *
 * @author admin
 */
public class Notepad implements ActionListener{

    /**
     * @param args the command line arguments
     */
    
    JMenuBar mbar;
    JMenu file,edit,format,help;
    JMenuItem fnew,open,save,saveAs,page,print,exit;
    JMenuItem undo,cut,copy,paste,delete,find,findnext,selectall,timedate;
    JMenuItem wordwrap,font,uppercase,lowercase,toggle;
    JMenuItem about,topics;
    JTextArea ta;
    JFrame f;
    final Clipboard clip = Toolkit.getDefaultToolkit().getSystemClipboard();
    String wholeText,findString;
    int ind=0;
    public Notepad()
    {
        f=new JFrame("Notepad");
        mbar  =new JMenuBar();
        f.setJMenuBar(mbar);
        ta=new JTextArea();
        f.add(ta);
        //ta.addTextListener(this);
        file = new JMenu("File");
        file.setMnemonic(KeyEvent.VK_F);
        edit=new JMenu("Edit");
        edit.setMnemonic(KeyEvent.VK_E);
        format = new JMenu("Format");
        format.setMnemonic(KeyEvent.VK_O);
        help=new JMenu("Help");
        help.setMnemonic(KeyEvent.VK_H);
        fnew = new JMenuItem("New");
        fnew.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,ActionEvent.CTRL_MASK));
        fnew.addActionListener(this);
        open=new JMenuItem("Open");
        open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,ActionEvent.CTRL_MASK));
        open.addActionListener( this);
        save=new JMenuItem("Save");
        save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,ActionEvent.CTRL_MASK));
        save.addActionListener( this);
        saveAs=new JMenuItem("SaveAs");
        saveAs.addActionListener( this);
        page=new JMenuItem("PageSetup...");
        page.addActionListener( this);
        print=new JMenuItem("Print...");
        print.addActionListener( this);
         exit=new JMenuItem("Exit");
        exit.addActionListener( this);
         undo=new JMenuItem("Undo");
        undo.addActionListener( this);
         cut=new JMenuItem("Cut");
        cut.addActionListener(this);
         copy=new JMenuItem("Copy");
        copy.addActionListener( this);
       paste=new JMenuItem("Paste");
        paste.addActionListener( this);
         delete=new JMenuItem("Delete");
        delete.addActionListener(this);
         find=new JMenuItem("Find");
       find.addActionListener( this);
        findnext=new JMenuItem("FindNext");
        findnext.addActionListener( this);
         selectall=new JMenuItem("Select All");
        selectall.addActionListener( this);
         timedate=new JMenuItem("Time/Date");
        timedate.addActionListener(this);
         wordwrap=new JMenuItem("Word Wrap");
        wordwrap.addActionListener( this);
         font=new JMenuItem("Font...");
        font.addActionListener( this);
         uppercase=new JMenuItem("Uppercase");
        uppercase.addActionListener( this);
         lowercase=new JMenuItem("Lowercase");
        lowercase.addActionListener( this);
         toggle=new JMenuItem("Toggle");
        toggle.addActionListener( this);
         about=new JMenuItem("About Notepad");
        about.addActionListener(this);
         topics=new JMenuItem("Help Topics");
        topics.addActionListener( this);
        
        
        
        file.add(fnew);
         file.add(open);
          file.add(save);
           file.add(saveAs);
            file.addSeparator();
             file.add(page);
              file.add(print);
               file.addSeparator();
               
               edit.add(undo);
                edit.addSeparator();
                 edit.add(cut);
                  edit.add(copy);
                   edit.add(paste);
                    edit.add(delete);
                     edit.addSeparator();
                      edit.add(find);
                       edit.add(findnext);
                        edit.addSeparator();
                         edit.add(selectall);
                          edit.add(timedate);
                          
                          format.add(wordwrap);
                          format.add(font);
                          format.add(uppercase);
                          format.add(lowercase);
                          format.add(toggle);
                          
                          help.add(topics);
                          help.addSeparator();
                          help.add(about);
                          
                          mbar.add(file);
                          mbar.add(edit);
                          mbar.add(format);
                          mbar.add(help);
                          
                          f.setBounds(100,100, 600,500);
                          f.setVisible(true);
                          f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                          
                          
                          
        
    }
   
    public static void main(String[] args) {
        // TODO code application logic here
        new Notepad();
    }
    public void actionPerformed(ActionEvent ae)
    {
     JMenuItem mi=(JMenuItem)ae.getSource();
     try{
         if (mi==fnew)
         {
             ta.setText("");
             
         }
         else if(mi==open)
         {
             FileDialog fd = new FileDialog(f,"OPEN",FileDialog.LOAD); 
            fd.setVisible(true);
            File file=new File(fd.getDirectory()+"\\"+fd.getFile());
            Reader r=new BufferedReader(new FileReader(file));
            char ch[]=new char[(int)file.length()];
            r.read(ch,0,ch.length);
            String s=new String(ch);
            
             ta.setText(s);
             r.close();
             f.setTitle(fd.getFile()+"-Notepad");
             
             
         }
         else if (mi==save)
         {
             FileDialog fds = new FileDialog(f,"SAVE",FileDialog.LOAD); 
             fds.setVisible(true);
             System.out.println("get dir === "+fds.getDirectory());
             Writer w=new FileWriter(fds.getDirectory()+"\\"+fds.getFile());
             String str = ta.getText();
             w.write(str);
             w.close();
             f.setTitle(fds.getFile()+"-Notepad2");
             
         }
         else if(mi==saveAs)
         {
              FileDialog fds = new FileDialog(f,"SAVE",FileDialog.LOAD); 
             fds.setVisible(true);
             Writer w=new FileWriter(fds.getDirectory()+"\\"+fds.getFile());
             String str = ta.getText();
             w.write(str);
             w.close();
             f.setTitle(fds.getFile()+"-Notepad2");
             
         }
          else if (mi==page )
         {
             PrinterJob pj = PrinterJob.getPrinterJob();
             {
                 PageFormat pf = pj.pageDialog(pj.defaultPage());
             }
             
         }
          else if (mi==print)
         {
             
         
          f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         }               
          else if (mi==undo)
         {
             
         }
          else if (mi==cut)
         {
             String selection = ta.getSelectedText();
             StringSelection data=new StringSelection(selection);
         }
          else if (mi==copy)
         {
             String selection = ta.getSelectedText();
             StringSelection data = new StringSelection(selection);
             clip.setContents(data, null);
         }
          else if (mi==paste)
         {
             Transferable clipData=clip.getContents(clip);
             try{
                 if(clipData.isDataFlavorSupported(DataFlavor.stringFlavor))
                 {
                     String s = (String)(clipData.getTransferData(DataFlavor.stringFlavor));
                     ta.replaceSelection(s);
                 }
             }
             catch(Exception a)
             {
                 
             }
         }
          else if (mi==delete)
         {
             String selection = ta.getSelectedText();
             StringSelection data = new StringSelection(selection);
             clip.setContents(data, data);
             ta.replaceRange("",ta.getSelectionStart(),ta.getSelectionEnd());
                     
             
         }
          else if (mi==find)
         {
             wholeText=ta.getText();
             findString=JOptionPane.showInputDialog(f,"Findwhat","Findtitle",JOptionPane.INFORMATION_MESSAGE);
             ind=wholeText.indexOf(findString,0);
             if(ind>=0)
             {
                 ta.setCaretPosition(ind);
                 ta.setSelectionStart(ind);
                 int a= ind+findString.length();
                 ta.setSelectionEnd(a);
             }
         }
          else if (mi==findnext)
         {
             wholeText=ta.getText();
             findString=JOptionPane.showInputDialog(null,"Findwhat","Findtitle",JOptionPane.INFORMATION_MESSAGE);
             ind=wholeText.indexOf(findString,ta.getCaretPosition());
             ta.setCaretPosition(ind);
             ta.setSelectionStart(ind);
             ta.setSelectionEnd(ind+findString.length());
         }
          else if (mi==selectall)
         {
             ta.selectAll();
         }
          else if (mi==timedate)
         {
          try
          {
              Thread.sleep(1000);
              Calendar cal=new GregorianCalendar();
              String hour=String.valueOf(cal.get(Calendar.HOUR));
              String minute=String.valueOf(cal.get(Calendar.MINUTE));
              
              String m1=String.valueOf(cal.get(Calendar.AM_PM));
              String d1=String.valueOf(cal.get(Calendar.DATE));
              String d2=String.valueOf(cal.get(Calendar.MONTH));
              String d3=String.valueOf(cal.get(Calendar.YEAR));
              ta.setText(String.valueOf(Calendar.getInstance().getTime()));
              
              
          }
          catch(Exception e1)
          {
              
          }
         
         }               
          else if (mi==wordwrap)
         {
             
         }
          else if (mi==font)
         {
             
         }
          else if(mi==uppercase)
          {
              String selection = ta.getSelectedText();
             String upper;
              upper=selection.toUpperCase();
            ta.replaceRange(upper,ta.getSelectionStart(),ta.getSelectionEnd());
              
          }
          else if (mi==lowercase)
          {
              String selection = ta.getSelectedText();
              String lower;
              lower=selection.toLowerCase();
              ta.replaceRange(lower,ta.getSelectionStart(),ta.getSelectionEnd());
          }
          else if (mi==toggle)
          {
              
              int c;
             
               char a[]=new char[(int) ta.getSelectedText().length()];
               for(c=0;c<a.length;c++)
               { 
                   a[c]=ta.getSelectedText().charAt(c);
          }
     for(c=0;c<a.length;c++)
     {
         if(a[c]>='A' && a[c]<='Z')
         {
             a[c]=(char)((int)a[c]+32);
         }
         else if(a[c]>='a' && a[c]<='z')
         {
             a[c]=(char)((int)a[c]-32);
         }
     }
           String s= new String(a);
             
           ta.replaceRange(s,ta.getSelectionStart(),ta.getSelectionEnd());
              
          }
         
     }
    catch(Exception e)
    {
        System.out.println("e.getMessage()");
    }
    }
}
