package br.com.sistema.redAmber.basicas.enums;

public enum TipoSala {
	LAB {
		@Override
		public String toString() {
			return "Laboratório";
		}
	},
	AULA {
		@Override
		public String toString() {
			return "Sala de Aula";
		}
	},
	ATELIE {
		@Override
		public String toString() {
			return "Ateliê";
		}
	}
}