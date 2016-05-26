package br.unb.cic.poo.gol

object Statistics {
  
  private var revivedCells = 0
	private var killedCells = 0
  
	
	def getRevivedCells = revivedCells

	def recordRevive = revivedCells += 1

  def getKilledCells = killedCells
	
  def recordKill = killedCells += 1 

	def display = {
		println("=================================");
		println("           Statistics            ");
		println("=================================");
		println("Revived cells: " + revivedCells);
		println("Killed cells: " + killedCells);
		println("=================================");
	}
  
}