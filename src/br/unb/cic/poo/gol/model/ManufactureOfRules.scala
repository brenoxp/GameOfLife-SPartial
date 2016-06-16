package br.unb.cic.poo.gol.model

import br.unb.cic.poo.gol.model.rules.Rule
import br.unb.cic.poo.gol.model.rules.Conway
import br.unb.cic.poo.gol.model.rules.Maze
import br.unb.cic.poo.gol.model.rules.DayNight
import br.unb.cic.poo.gol.model.rules.WalledCities

object ManufactureOfRules {
  
  def getRule(i: Int): Rule = {
    i match {
      case 0 => Conway
      case 1 => Maze
      case 2 => DayNight
      case 3 => WalledCities
    }
  }
  
}