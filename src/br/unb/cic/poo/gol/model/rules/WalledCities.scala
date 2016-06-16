package br.unb.cic.poo.gol.model.rules

object WalledCities extends Rule {
  
  val name = "Walled Cities"
  
  def shouldKeepAlive(isAlive: Boolean, aliveNeighbors: Int): Boolean = {
    (isAlive) && (aliveNeighbors == 2 || aliveNeighbors == 3 || 
				aliveNeighbors == 4 || aliveNeighbors == 5)
  }
  def shouldRevive(isAlive: Boolean, aliveNeighbors: Int): Boolean =  {
     (!isAlive) && (aliveNeighbors == 4 || aliveNeighbors == 5 || aliveNeighbors == 6 ||
						aliveNeighbors == 7 || aliveNeighbors == 8)
  }
}