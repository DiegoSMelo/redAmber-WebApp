package br.com.sistema.redAmber.basicas.enums;

public enum TipoAvisoProfessor {
	ATRASO {
		@Override
		public String toString() {
			return "Atraso";
		}
	},
	AUSENCIA {
		@Override
		public String toString() {
			return "Ausência";
		}
	}
}