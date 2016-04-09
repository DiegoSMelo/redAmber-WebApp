package br.com.sistema.redAmber.basicas.enums;

public enum StatusHoraAula {
	ATIVA {
		@Override
		public String toString() {
			return "Ativa";
		}
	},
	INATIVA {
		@Override
		public String toString() {
			return "Inativa";
		}
	}
}