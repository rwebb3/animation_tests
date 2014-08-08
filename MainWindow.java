import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
public class MainWindow {
	private Animations anim = new Animations();
	private JButton showButton,exitButton,addButton,removeButton;
	private JCheckBox checkBoat,checkCar,checkPlane,checkClock;
	public MainWindow(){
		showButton = new JButton("Show");
		exitButton = new JButton("Exit");
		addButton = new JButton("Add");
		removeButton = new JButton("Remove");
		checkBoat = new JCheckBox("boat");
		checkCar = new JCheckBox("car");
		checkPlane = new JCheckBox("plane");
		checkClock = new JCheckBox("clock");
	}
	public void display(){
		JFrame mainWin = new JFrame("Options");
		mainWin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainWin.setLayout(new FlowLayout());
		mainWin.pack();
		mainWin.setVisible(true);
		mainWin.setSize(500,100);
		/*button functions*/
		showButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				anim.run();
			}
		});
		exitButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				System.exit(0);
			}
		});
		addButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				if(checkBoat.isSelected()){
					anim.addShape("boat");
				}
				if(checkCar.isSelected()){
					anim.addShape("car");
				}
				if(checkPlane.isSelected()){
					anim.addShape("plane");
				}
				if(checkClock.isSelected()){
					anim.addShape("clock");
				}
			}
		});
		removeButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				anim.removeLast();
			}
		});


		/*add elements to main window*/
		mainWin.add(showButton);
		mainWin.add(exitButton);
		mainWin.add(addButton);
		mainWin.add(removeButton);
		mainWin.add(checkBoat);
		//mainWin.add(checkCar);
		checkPlane.setSelected(true);
		mainWin.add(checkPlane);
		mainWin.add(checkClock);
	}
}
