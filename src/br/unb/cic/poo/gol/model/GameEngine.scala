package br.unb.cic.poo.gol.model

import scala.collection.mutable.ListBuffer
import br.unb.cic.poo.gol.view.Cell
import br.unb.cic.poo.gol.Main
import br.unb.cic.poo.gol.view.commandline.Statistics
import br.unb.cic.poo.gol.controller.GameController
import br.unb.cic.poo.gol.model.rules.Rule

object GameEngine {
  
  val height = Main.height
  val width = Main.width
  
  val cells = Array.ofDim[Cell](height * width) map { x => new Cell }
  HistoryStates.add(cells)
  
  //Rule deve ser definido como def pois sua avaliação deve ser feita sempre que
  //for chamada. Isso porque poderá ser alterada durante a execução do programa
  def rule = Main.rule
  
  private def getListCellsItem(i: Int, j: Int): Cell ={
    cells(i * width + j)
  }
  
  private def setListCellsItem(i: Int, j: Int, cell: Cell){
    cells(i * width + j) = cell
  }

  /**
	 * Calcula uma nova geracao do ambiente. Essa implementacao utiliza o
	 * algoritmo do Conway, ou seja:
	 * 
	 * a) uma celula morta com exatamente tres celulas vizinhas vivas se torna
	 * uma celula viva.
	 * 
	 * b) uma celula viva com duas ou tres celulas vizinhas vivas permanece
	 * viva.
	 * 
	 * c) em todos os outros casos a celula morre ou continua morta.
	 */
  
  def nextGeneration {
    HistoryStates.add(cells)
    
    val mustRevive = new ListBuffer[Cell]
    val mustKill = new ListBuffer[Cell]

    for(i <- (0 until height)) {
      for(j <- (0 until width)) {
        
        if(rule.shouldRevive(getListCellsItem(i,j).isAlive, numberOfNeighborhoodAliveCells(i, j))){
          mustRevive += getListCellsItem(i,j)
        }
        else if(!rule.shouldKeepAlive(getListCellsItem(i,j).isAlive, numberOfNeighborhoodAliveCells(i, j))){
          mustKill += getListCellsItem(i,j)
        }
      }
    }
    
    for(cell <- mustRevive) {
      cell.revive
      Statistics.recordRevive
    }
    
    for(cell <- mustKill) {
      cell.kill
      Statistics.recordKill
    }
    
    GameController.updateView
  }
  
  def undoGeneration {
    if(HistoryStates.canUndo){
      val cellsUndo = HistoryStates.undo
      for(i <- 0 until cellsUndo.length){
        if(cellsUndo(i).isAlive) cells(i).revive
        else cells(i).kill
      }
    }
    GameController.updateView
  }
  

  /*
	 * Verifica se uma posicao (a, b) referencia uma celula valida no tabuleiro.
	 */
  private def validPosition(i: Int, j: Int) = 
    i >= 0 && i < height && j >= 0 && j < width;
  
  
  /**
	 * Torna a celula de posicao (i, j) viva
	 * 
	 * @param i posicao vertical da celula
	 * @param j posicao horizontal da celula
	 * 
	 * @throws InvalidParameterException caso a posicao (i, j) nao seja valida.
	 */
  @throws(classOf[IllegalArgumentException])
  def makeCellAlive(i: Int, j: Int) = {
    if(validPosition(i, j)){
      getListCellsItem(i,j).revive
      Statistics.recordRevive
    } else {
      throw new IllegalArgumentException
    }
  }
  
  /**
	 * Verifica se uma celula na posicao (i, j) estah viva.
	 * 
	 * @param i Posicao vertical da celula
	 * @param j Posicao horizontal da celula
	 * @return Verdadeiro caso a celula de posicao (i,j) esteja viva.
	 * 
	 * @throws InvalidParameterException caso a posicao (i,j) nao seja valida. 
	 */
  @throws(classOf[IllegalArgumentException])
  def isCellAlive(i: Int, j: Int): Boolean = {
    if(validPosition(i, j)) {
      getListCellsItem(i,j).isAlive
    } else {
      throw new IllegalArgumentException
    }
  }
  
  
  /**
	 * Retorna o numero de celulas vivas no ambiente. 
	 * Esse metodo eh particularmente util para o calculo de 
	 * estatisticas e para melhorar a testabilidade.
	 * 
	 * @return  numero de celulas vivas.
	 */
  def numberOfAliveCells {
    var aliveCells = 0    
    for(cell <- cells) if(cell.isAlive) aliveCells += 1
  }
  
  /*
	 * Computa o numero de celulas vizinhas vivas, dada uma posicao no ambiente
	 * de referencia identificada pelos argumentos (i,j).
	 */
  private def numberOfNeighborhoodAliveCells(i: Int, j: Int): Int = {
    var alive = 0
    for(a <- (i - 1 to i + 1)) {
      for(b <- (j - 1 to j + 1)) {
        val a1 = convertIToInfiniteWorld(a)
				val b1 = convertJToInfiniteWorld(b)
				
        if (validPosition(a1, b1)  && (!(a1==i && b1 == j)) && getListCellsItem(a1,b1).isAlive) {
					alive += 1
				}
      }
    }
    alive
  }
  
   /* 
  	 * Verifica se i eh uma posicao menor do que a origem do eixo x, 
  	 * caso seja, i passa a ser o maior valor aceito de x.
  	 * Se i for uma posicao maior do que o maior valor aceito para o eixo x,
  	 * entao o valor de i passa a ser o menor valor aceito de x.
	 */
  def convertIToInfiniteWorld(i: Int): Int = {
    if(i == -1) width - 1 
    else if(i == width) 0
    i
  }
  
  /* O mesmo tratamento para j */
  def convertJToInfiniteWorld(j: Int): Int = {
    if(j == -1) height - 1 
    else if(j == height) 0
    j
  }
  
  /* faz com que um número aleatório de células fiquem vivas*/
  def randomCellsAlive() {
    HistoryStates.add(cells)
    
    def randomValue = scala.util.Random.nextInt(100)
    for(cell <- cells) if(randomValue < 50) cell.revive else cell.kill
   
    GameController.updateView
  }
  
  
  def halt() {
    println("\n \n")
    Statistics.display
    System.exit(0)
  }
  
  
  

}