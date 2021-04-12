package com.AWT;

import java.awt.*;
import java.awt.event.*;

public class Main {

    // создаем в классе пееменные
    private Frame mainFrame; // фрайм и 2 метки (лэйблы) и панель
    private Label headerLabel;
    private Label statusLabel;
    private Panel controlPanel;

    public Main() { // ----------------------------------------- конструктор для нашего класса
        prepareGUI(); // и вызываемый метод prepareGUI
    }

    public static void main(String[] args) {
        Main myMainClass = new Main(); // создание наешого основного класса
        myMainClass.showMenuDemo(); // и вызываемый метод showMenuDemo
    }

    // ----------------------------------------------------- описывает основное визуальное отображение
    private void prepareGUI() {
        mainFrame = new Frame("Java AWT Menu");  // инициализация фрэйма
        mainFrame.setSize(400, 400); // размеры
        mainFrame.setLayout(new GridLayout(3, 1));// расположение объектов на форме

        mainFrame.addWindowListener(new WindowAdapter() {// обработка событий
            public void windowClosing(WindowEvent windowEvent) { // принажатии на кнопку "Закрыть"
                System.exit(0);
            }
        });

        // метки
        headerLabel = new Label(); // инициализация
        headerLabel.setAlignment(Label.CENTER);// в центре
        statusLabel = new Label();// инициализация
        statusLabel.setAlignment(Label.CENTER);// в центре
        statusLabel.setSize(350, 100);// размеры

        controlPanel = new Panel();// новая панель
        controlPanel.setLayout(new FlowLayout()); // расположение объектов на форме (лайаут)

        // добавление объектов на форму
        mainFrame.add(headerLabel);
        mainFrame.add(controlPanel);
        mainFrame.add(statusLabel);
        mainFrame.setVisible(true); // видимость формы true
    }

    //----------------------------------------------------- Что будет в showMenuDemo
    private void showMenuDemo(){
        //создание полосы меню
        final MenuBar menuBar = new MenuBar();

        //создание самого меню
        Menu fileMenu = new Menu("File");
        Menu editMenu = new Menu("Edit");
        final Menu aboutMenu = new Menu("About");

        //создание полей меню
        MenuItem newMenuItem =
                new MenuItem("New",new MenuShortcut(KeyEvent.VK_N)); // поле New
        newMenuItem.setActionCommand("New");

        MenuItem openMenuItem = new MenuItem("Open"); // поле Open
        openMenuItem.setActionCommand("Open");

        MenuItem saveMenuItem = new MenuItem("Save"); // поле Save
        saveMenuItem.setActionCommand("Save");

        MenuItem exitMenuItem = new MenuItem("Exit"); // поле Exit
        exitMenuItem.setActionCommand("Exit");

        MenuItem cutMenuItem = new MenuItem("Cut"); // поле Cut
        cutMenuItem.setActionCommand("Cut");

        MenuItem copyMenuItem = new MenuItem("Copy"); // поле Copy
        copyMenuItem.setActionCommand("Copy");

        MenuItem pasteMenuItem = new MenuItem("Paste"); // поле Paste
        pasteMenuItem.setActionCommand("Paste");

        MenuItemListener menuItemListener = new MenuItemListener(); // список полей меню

        newMenuItem.addActionListener(menuItemListener);
        openMenuItem.addActionListener(menuItemListener);
        saveMenuItem.addActionListener(menuItemListener);
        exitMenuItem.addActionListener(menuItemListener);
        cutMenuItem.addActionListener(menuItemListener);
        copyMenuItem.addActionListener(menuItemListener);
        pasteMenuItem.addActionListener(menuItemListener);

        final CheckboxMenuItem showWindowMenu = // галочка, меню
                new CheckboxMenuItem("Show About", true);
        showWindowMenu.addItemListener(new ItemListener() { // действие на голочке
            public void itemStateChanged(ItemEvent e) {
                if(showWindowMenu.getState()){ // true?
                    menuBar.add(aboutMenu);//добавить
                }else{
                    menuBar.remove(aboutMenu);//удалить
                }
            }
        });

        //добавить подпункты меню в основное меню
        fileMenu.add(newMenuItem);
        fileMenu.add(openMenuItem);
        fileMenu.add(saveMenuItem);
        fileMenu.addSeparator();
        fileMenu.add(showWindowMenu);
        fileMenu.addSeparator();
        fileMenu.add(exitMenuItem);

        editMenu.add(cutMenuItem);
        editMenu.add(copyMenuItem);
        editMenu.add(pasteMenuItem);

        //добавить меню в меню бар(полоска сверху)
        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(aboutMenu);

        //добавить меню бар на форму
        mainFrame.setMenuBar(menuBar);
        mainFrame.setVisible(true);
    }

    class MenuItemListener implements ActionListener { // действие при нажатии на меню
        public void actionPerformed(ActionEvent e) {
            statusLabel.setText(e.getActionCommand()
                    + " MenuItem clicked.");
        }
    }
}
