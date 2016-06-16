package br.unb.cic.poo.gol.view.commandline


import scala.io.StdIn.{readInt, readLine}
import br.unb.cic.poo.gol.controller.GameController
import br.unb.cic.poo.gol.model.GameEngine
import br.unb.cic.poo.gol.view.GameView
import br.unb.cic.poo.gol.Main
import br.unb.cic.poo.gol.model.ManufactureOfRules


object CommandLineView extends GameView {
  private final val LINE = "+-----+"
	private final val DEAD_CELL = "|     |"
	private final val ALIVE_CELL = "|  o  |"
	
	private final val INVALID_OPTION = 0
	private final val MAKE_CELL_ALIVE = 1
	private final val NEXT_GENERATION = 2
	private final val MAKE_RANDOM_CELLS_ALIVE = 3
	private final val CHANGE_RULE = 4
	private final val HALT = 5
	
	def startView {
    update
  }
  
  /**
	 * Atualiza o componente view (representado pela classe GameBoard),
	 * possivelmente como uma resposta a uma atualizacao do jogo.
	 */
	def update {
		printFirstRow
		printLine
		
		for(i <- (0 until GameEngine.height)) {
		  for(j <- (0 until GameEngine.width)) {
		    print(if (GameEngine.isCellAlive(i, j))  ALIVE_CELL else DEAD_CELL);
		  }
		  println("   " + i)
		  printLine
		}
		printOptions
	}
  
  private def printOptions {
	  var option = 0
	  println("\n\n")
	  
	  do{
	    println("Select one of the options: \n \n"); 
			println("[1] Make a cell alive");
			println("[2] Next generation");
			println("[3] Make random cells alive");
			println("[4] Change rule");
			println("[5] Halt");
		
			print("\n \n Option: ");
			
			option = parseOption(readLine)
	  }while(option == 0)
	  
	  option match {
      case MAKE_CELL_ALIVE => makeCellAlive
      case NEXT_GENERATION => nextGeneration
      case MAKE_RANDOM_CELLS_ALIVE => randomCellsAlive
      case CHANGE_RULE => changeRule
      case HALT => halt
    }
	}
  
  
  private def changeRule(){
    println("Select one of the rules: \n \n"); 
		println("[1] Conway");
		println("[2] Maze");
		println("[3] Day Night");
		println("[4] Walled Cities");
		println("[5] Cancel");
		
		print("\n \n Option: ");
		
		val option = readLine
		
		Main.rule = option match {
		  case "1" => ManufactureOfRules.getRule(1)
		  case "2" => ManufactureOfRules.getRule(2)
		  case "3" => ManufactureOfRules.getRule(3)
		  case "4" => ManufactureOfRules.getRule(4)
		}
		
		update
		printOptions
  }
  
  private def makeCellAlive {
	  var i = 0
	  var j = 0
	  
	  do {
      print("\n Inform the row number (0 - " + (GameEngine.height - 1) + "): ")
      i = readInt
      
      print("\n Inform the column number (0 - " + (GameEngine.width - 1) + "): ")
      j = readInt
      
    } while(!validPosition(i,j))
      
    GameController.makeCellAlive(i, j)
	}

  private def nextGeneration = GameController.nextGeneration
  private def randomCellsAlive = GameController.randomCellsAlive
  private def halt = GameController.halt
	
  private def validPosition(i: Int, j: Int): Boolean = {
		println(i);
		println(j);
		i >= 0 && i < GameEngine.height && j >= 0 && j < GameEngine.width
	}
  
	private def parseOption(option: String): Int = option match {
    case "1" => MAKE_CELL_ALIVE
    case "2" => NEXT_GENERATION
    case "3" => MAKE_RANDOM_CELLS_ALIVE
    case "4" => CHANGE_RULE
    case "5" => HALT
    case _ => INVALID_OPTION
  }
	
  
  /* Imprime uma linha usada como separador das linhas do tabuleiro */
	private def printLine() {
	  for(j <- (0 until GameEngine.width)) {
	    print(LINE)
	  }
	  println()
	}
  
  /*
	 * Imprime os identificadores das colunas na primeira linha do tabuleiro
	 */
	private def printFirstRow {
		println("\n \n");
		
		for(j <- (0 until GameEngine.width)) {
		  print("   " + j + "   ")
		}
		println()
	}
}