package br.unb.cic.poo.gol

import br.unb.cic.poo.gol.controller.GameController
import br.unb.cic.poo.gol.view.GameView
import br.unb.cic.poo.gol.view.commandline.CommandLineView
import br.unb.cic.poo.gol.view.gui.GUIView
import br.unb.cic.poo.gol.model.ManufactureOfRules
import br.unb.cic.poo.gol.model.ManufactureOfViews

object Main {
  
  val height = 10
  val width = 10
  
  var rule = ManufactureOfRules.getRule(1)
  val view = ManufactureOfViews.getView(0)
  
  def main(args: Array[String]){ 
    GameController.start
  }
}