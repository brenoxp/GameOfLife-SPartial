package br.unb.cic.poo.gol.model.rules

object Maze extends Rule {
  
  val name = "Maze"
  
  def shouldKeepAlive(isAlive: Boolean, aliveNeighbors: Int): Boolean = {
    (isAlive) && (aliveNeighbors == 1 || aliveNeighbors == 2 || aliveNeighbors == 3 || 
				aliveNeighbors == 4 || aliveNeighbors == 5)
  }
  def shouldRevive(isAlive: Boolean, aliveNeighbors: Int): Boolean =  {
     (!isAlive) && (aliveNeighbors == 3)
  }
}