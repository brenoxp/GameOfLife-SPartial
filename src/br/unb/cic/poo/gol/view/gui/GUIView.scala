package br.unb.cic.poo.gol.view.gui

import br.unb.cic.poo.gol.view.GameView
import scala.swing._
import javax.swing.JPanel
import java.awt.BorderLayout

class GUIView extends SimpleSwingApplication  {
  
//  var controls = 0
  
  def top = new MainFrame {
    title = "Game of Life"
    size = new Dimension(700, 700)
    resizable = false
    
//    controls = createButtonsMenu(window)
    
    contents = new Button {
      text = "Click Me!"
    }
  }
  
  
  def createButtonsMenu(window: Frame) {
    
  }
  
//  def startup(args: Array[String]){
//    val window = new Frame
//    window.title = "Game of Life"
//    window.size = new Dimension(700, 700)
//    window.visible = true
//    window.resizable = false
//    
//  }
  

  
  def update {
    
  }
  
}