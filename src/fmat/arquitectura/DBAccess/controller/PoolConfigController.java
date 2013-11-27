package fmat.arquitectura.DBAccess.controller;

import fmat.arquitectura.DBAccess.modelo.PoolConfigInfo;

public class PoolConfigController {
	
		private static PoolConfigController poolConfigController = new PoolConfigController();
		private PoolConfigController(){}
	
		public PoolConfigController getPoolConfigController(){
			return poolConfigController;
		}
		
		public PoolConfigInfo getPoolConfigInfo(){
			return new PoolConfigInfo();
		}
	
}
