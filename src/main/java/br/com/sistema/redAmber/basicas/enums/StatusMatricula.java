package br.com.sistema.redAmber.basicas.enums;

public enum StatusMatricula {
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