package br.unb.cic.poo.gol.model.rules

object Conway extends Rule {
  
  val name = "Conway"
  
  def shouldKeepAlive(isAlive: Boolean, aliveNeighbors: Int): Boolean = {
    isAlive && (aliveNeighbors == 2 || aliveNeighbors == 3)
  }
  def shouldRevive(isAlive: Boolean, aliveNeighbors: Int): Boolean =  {
    !isAlive && (aliveNeighbors == 3)
  }
}