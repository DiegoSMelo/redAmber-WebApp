package br.com.sistema.redAmber.basicas.enums;

public enum TipoCurso {

	SUPERIOR {
		@Override
		public String toString() {
			return "Superior";
		}
	},

	TECNICO {
		@Override
		public String toString() {
			return "Técnico";
		}
	}
}
