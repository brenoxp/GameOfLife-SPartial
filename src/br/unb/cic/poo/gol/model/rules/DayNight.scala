package br.unb.cic.poo.gol.model.rules


object DayNight extends Rule {
  
  val name = "Day Night"
  
  def shouldKeepAlive(isAlive: Boolean, aliveNeighbors: Int): Boolean = {
    (isAlive) && (aliveNeighbors == 3 || aliveNeighbors == 4 || aliveNeighbors == 6 || 
				aliveNeighbors == 7 || aliveNeighbors == 8)
  }
  def shouldRevive(isAlive: Boolean, aliveNeighbors: Int): Boolean =  {
     (!isAlive) && (aliveNeighbors == 3 || aliveNeighbors == 6 || aliveNeighbors == 7 ||
				aliveNeighbors == 8)
  }
}


