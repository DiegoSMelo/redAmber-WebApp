package br.com.sistema.redAmber.basicas.enums;

public enum TipoTurno {

	MANHA {
		@Override
		public String toString() {
			return "Manhã";
		}
	}
	,
	TARDE {
		@Override
		public String toString() {
			return "Tarde";
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
