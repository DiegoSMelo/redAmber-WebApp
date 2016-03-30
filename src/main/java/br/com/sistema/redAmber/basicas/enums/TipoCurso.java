package br.com.sistema.redAmber.basicas.enums;

public enum TipoCurso {
	POS {
		@Override
		public String toString() {
			return "PÓS";
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
			return "TÉCNICO";
		}
	}
}
