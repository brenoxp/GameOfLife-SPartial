package br.unb.cic.poo.gol.model

import br.unb.cic.poo.gol.model.rules.Rule
import br.unb.cic.poo.gol.model.rules.Conway
import br.unb.cic.poo.gol.model.rules.Maze
import br.unb.cic.poo.gol.model.rules.DayNight
import br.unb.cic.poo.gol.model.rules.WalledCities

object ManufactureOfRules {
  
  def getRule(i: Int): Rule = {
    i match {
      case 1 => Conway
      case 2 => Maze
      case 3 => DayNight
      case 4 => WalledCities
    }
  }
  
}