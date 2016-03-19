package br.com.sistema.redAmber.basicas.enums;

public enum TipoTurno {

	MANHA {
		@Override
		public String toString() {
			return "Manhã";
		}
	}

	,

	NOITE {
		@Override
		public String toString() {
			return "Noite";
		}
	}
}
