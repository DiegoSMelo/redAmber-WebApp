package br.com.sistema.redAmber.basicas.enums;

public enum TipoCurso {
	POS {
		@Override
		public String toString() {
			return "P�S";
		}
	},

	SUPERIOR {
		@Override
		public String toString() {
			return "SUPERIOR";
		}
	},
	
	 TECNICO {
		@Override
		public String toString() {
			return "T�CNICO";
		}
	}
}
