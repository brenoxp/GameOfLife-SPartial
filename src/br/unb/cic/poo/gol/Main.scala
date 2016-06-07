package br.unb.cic.poo.gol

import scala.collection.mutable.ListBuffer
import br.unb.cic.poo.gol.controller.GameController
import br.unb.cic.poo.gol.view.GameView
import br.unb.cic.poo.gol.view.CommandLineView
import br.unb.cic.poo.gol.view.gui.GUIView

object Main {
  
  val height = 10
  val width = 10
  
//  val view = CommandLineView
  val view = new GUIView
  
  def main(args: Array[String]){
    
    view.startup(Array("test"))
    
    GameController.start
  }
}