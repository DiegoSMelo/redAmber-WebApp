package br.com.sistema.redAmber.basicas.enums;

public enum TipoSala {
	LAB {
		@Override
		public String toString() {
			return "Laborat�rio";
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
			return "Ateli�";
		}
	}
}