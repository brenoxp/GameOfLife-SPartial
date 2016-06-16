package br.unb.cic.poo.gol.model

import br.unb.cic.poo.gol.view.GameView
import br.unb.cic.poo.gol.view.commandline.CommandLineView
import br.unb.cic.poo.gol.view.gui.GUIView

object ManufactureOfViews {
  def getView(i: Int): GameView = {
    i match {
      case 0 => CommandLineView
      case 1 => GUIView
    }
  }
}