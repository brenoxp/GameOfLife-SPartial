package br.unb.cic.poo.gol.model.rules

trait Rule {
  
  val name: String
  
  def shouldKeepAlive(isAlive: Boolean, aliveNeighbors: Int): Boolean
  def shouldRevive(isAlive: Boolean, aliveNeighbors: Int): Boolean
}