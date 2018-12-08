package com.mercadolibre.mutantfinder.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mercadolibre.mutantfinder.dao.StatsRepository;
import com.mercadolibre.mutantfinder.entity.Stats;
import com.mercadolibre.mutantfinder.excepction.IMutantFinderService;

@Service
public class MutantFinderService implements IMutantFinderService {

	private static final Logger logger = LoggerFactory.getLogger(MutantFinderService.class);

	@Autowired
	private StatsRepository repositoryStats;

	public String[][] matriz;
	public int filas = 0;
	public int columnas = 0;

	private String[] split;

	public boolean isMutant(String[] dna) {
		logger.info("@..isMutant");
		toSizeUpMatrix(dna);
		toMatrix(dna);
		boolean isMutant = matrixChecker();
		updateStats(isMutant);
		return isMutant;
	}

	private void updateStats(boolean isMutant) {
		logger.info("@..updateStats");
		int mutCounter = 0;
		int humCounter = 0;
		String rat = "";
		
		Stats stat = null;
		
		List<Stats> stats = repositoryStats.findAll();
		
		if (stats != null && !stats.isEmpty()) {
			stat = stats.get(0);
			humCounter = stat.getCount_human_dna();
			mutCounter = stat.getCount_mutant_dna();
		}
		
		if (isMutant) {
			mutCounter++;
		} else {
			humCounter++;
		}
		if (mutCounter != 0) {
			rat = (new BigDecimal(humCounter / mutCounter)).toString();
		}else {
			rat = Integer.toString(0);
		}
		
		if (stats != null && !stats.isEmpty()) {
			stat.setCount_human_dna(humCounter);
			stat.setCount_mutant_dna(mutCounter);
			stat.setRatio(rat);
			logger.info("@..updating stat");
			repositoryStats.save(stat);
		}
		else {
			logger.info("@..new stat");
			stat = new Stats(mutCounter, humCounter, rat);
			repositoryStats.save(stat);
		}
	}

	private void toSizeUpMatrix(String[] dna) {
		logger.info("@..toSizeUpMatrix");
		for (String string : dna) {
			if (columnas == 0 || string.length() > columnas) {
				columnas = string.length();
			}
			filas++;
		}
		logger.info("@..filas " + filas);
		logger.info("@..columnas " + columnas);
		matriz = new String[filas][columnas];
	}

	private boolean matrixChecker() {
		logger.info("@..matrixChecker");

		for (int i = 0; i < filas; i++) {
			for (int j = 0; j < columnas; j++) {
				if (checkPosition(i, j)) {
					logger.info("MUTANT FOUND!");
					return true;
				}
			}
		}
		logger.info("HUMAN FOUND!");
		return false;
	}

	private boolean checkPosition(int i, int j) {
		logger.info("@..checkPosition");
		return checkDiagUpLeft(i, j) || checkUp(i, j) || checkDiagUpRight(i, j) || checkLeft(i, j)
				|| /* middle */ checkRight(i, j) || checkDiagDownLeft(i, j) || checkDown(i, j)
				|| checkDiagDownRight(i, j);
	}

	private boolean checkDiagDownRight(int i, int j) {
		if (i + 3 > filas || j + 3 > columnas) {
			return false;
		} else {
			return allEqual(matriz[i][j], matriz[i + 1][j + 1], matriz[i + 2][j + 2], matriz[i + 3][j + 3]);
		}
	}

	private boolean checkDown(int i, int j) {
		if (i + 3 > filas) {
			return false;
		} else {
			return allEqual(matriz[i][j], matriz[i + 1][j], matriz[i + 2][j], matriz[i + 3][j]);
		}
	}

	private boolean checkDiagDownLeft(int i, int j) {
		if (i + 3 > filas || j - 3 < 0) {
			return false;
		} else {
			return allEqual(matriz[i][j], matriz[i + 1][j - 1], matriz[i + 2][j - 2], matriz[i + 3][j - 3]);
		}
	}

	private boolean checkRight(int i, int j) {
		if (j + 3 > columnas) {
			return false;
		} else {
			return allEqual(matriz[i][j], matriz[i][j + 1], matriz[i][j + 2], matriz[i][j + 3]);
		}
	}

	private boolean checkLeft(int i, int j) {
		if (j - 3 < 0) {
			return false;
		} else {
			return allEqual(matriz[i][j - 3], matriz[i][j - 2], matriz[i][j - 1], matriz[i][j]);
		}
	}

	private boolean checkDiagUpRight(int i, int j) {
		if (i - 3 < 0 || j + 3 > columnas) {
			return false;
		} else {
			return allEqual(matriz[i - 3][j + 3], matriz[i - 2][j + 2], matriz[i - 1][j + 1], matriz[i][j]);
		}
	}

	private boolean checkUp(int i, int j) {
		if (i - 3 < 0) {
			return false;
		} else {
			return allEqual(matriz[i - 3][j], matriz[i - 2][j], matriz[i - 1][j], matriz[i][j]);
		}
	}

	private boolean checkDiagUpLeft(int i, int j) {
		if (i - 3 < 0 || j - 3 < 0) {
			return false;
		} else {
			return allEqual(matriz[i - 3][j - 3], matriz[i - 2][j - 2], matriz[i - 1][j - 1], matriz[i][j]);
		}
	}

	private boolean allEqual(String string, String string2, String string3, String string4) {
		return string.equalsIgnoreCase(string2) && string2.equalsIgnoreCase(string3)
				&& string3.equalsIgnoreCase(string4);
	}

	public void toMatrix(String[] dna) {
		logger.info("@..toMatrix");
		for (int i = 0; i < filas; i++) {
			split = dna[i].split("");
			for (int j = 0; j < columnas; j++) {
				matriz[i][j] = split[j];
			}
		}
	}
}
