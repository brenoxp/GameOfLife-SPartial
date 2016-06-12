package br.unb.cic.poo.gol.controller

import br.unb.cic.poo.gol.model.GameEngine
import br.unb.cic.poo.gol.view.GameView
import br.unb.cic.poo.gol.view.commandline.Statistics
import br.unb.cic.poo.gol.Main

object GameController {
  
  val view = Main.view
  
  def start {
    view.startView
  }
  
  

  def makeCellAlive(i: Int, j: Int) {
    try {
			GameEngine.makeCellAlive(i, j)
			view.update
		}
		catch {
		  case ex: IllegalArgumentException => {
		    println(ex.getMessage)
		  }
		}
  }
  
  def nextGeneration {
    GameEngine.nextGeneration
    view.update
  }
  
  def halt {
    GameEngine.halt()
  }
  
}