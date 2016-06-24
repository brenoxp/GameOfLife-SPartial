package br.unb.cic.poo.gol.model

import br.unb.cic.poo.gol.view.Cell
import scala.collection.mutable.ArrayBuffer
import scala.math.pow

object HistoryStates {
  
  private val MAX_STATES = 30

  val states = new ArrayBuffer[Array[Cell]]

  def add(cells: Array[Cell]) {
    if(states.size >= MAX_STATES) states.remove(0)
    states += cells map (cell => cell.clone())
  }
  
  def undo: Array[Cell] = states.remove(states.size - 1)
  
  def canUndo: Boolean = {
    if(states.size > 1) true
    else false
  }
  
}