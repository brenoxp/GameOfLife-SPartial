package br.unb.cic.poo.gol

class Cell {
  
  private var alive = false
  
  def isAlive = alive
  
  def kill = alive = false
  def revive = alive = true
}