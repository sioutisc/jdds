/* ********************************************************************* *
 *                                                                       *
 *   =============================================================       *
 *   Copyright 2011                                                      *
 *   Christos Sioutis <christos.sioutis@gmail.com>                       *
 *   =============================================================       *
 *                                                                       *
 *   This file is part of jdds.                                          *
 *                                                                       *
 *   jdds is free software: you can redistribute it and/or               *
 *   modify it under the terms of the GNU General Public License         *
 *   as published by the Free Software Foundation, either version 3 of   *
 *   the License, or (at your option) any later version.                 *
 *                                                                       *
 *   jdds is distributed in the hope that it will be useful,             *
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of      *
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the       *
 *   GNU General Public License for more details.                        *
 *                                                                       *
 *   You should have received a copy of the GNU General Public           *
 *   License along with jdds.                                            *
 *   If not, see <http://www.gnu.org/licenses/>.                         *
 *                                                                       *
 * ********************************************************************* */


package RTPS;

// Nov 2013
// http://portals.omg.org/dds/content/page/dds-rtps-vendor-and-product-ids

// if this starts working, should apply to OMG for a proper Vendor ID

public interface VENDORID {
	public static byte[] rawRTI 		= {1,1};
	public static VendorId_t RTI = new VendorId_t(rawRTI);
	
	public static byte[] rawPrismTech 	= {1,2};
	public static VendorId_t PrismTech = new VendorId_t(rawPrismTech);
	
	public static byte[] rawOCI 		= {1,3};
	public static VendorId_t OCI = new VendorId_t(rawOCI);
	
	public static byte[] rawMilSoft 	= {1,4};
	public static VendorId_t MilSoft = new VendorId_t(rawMilSoft);
	
	public static byte[] rawGallium 	= {1,5};
	public static VendorId_t Gallium = new VendorId_t(rawGallium);
	
	public static byte[] rawTwinOaks 	= {1,6};
	public static VendorId_t TwinOaks = new VendorId_t(rawTwinOaks);
	
	public static byte[] rawLakota 		= {1,7};
	public static VendorId_t Lakota = new VendorId_t(rawLakota);
	
	public static byte[] rawIcoup 		= {1,8};
	public static VendorId_t Icoup = new VendorId_t(rawIcoup);

	public static byte[] rawETRI 		= {1,9};
	public static VendorId_t ETRI = new VendorId_t(rawETRI);

	public static byte[] rawRTIMicro	= {1,0xA};
	public static VendorId_t RTIMicro = new VendorId_t(rawRTIMicro);
	
	public static byte[] rawPrismTechMobile	= {1,0xB};
	public static VendorId_t PrismTechMobile = new VendorId_t(rawPrismTechMobile);
	
	public static byte[] rawPrismTechGateway	= {1,0xC};
	public static VendorId_t PrismTechGateway = new VendorId_t(rawPrismTechGateway);
	
	public static byte[] rawPrismTechLite	= {1,0xD};
	public static VendorId_t PrismTechLite = new VendorId_t(rawPrismTechLite);
	
	public static byte[] rawTechnicolor	= {1,0xE};
	public static VendorId_t Technicolor = new VendorId_t(rawTechnicolor);

	public static byte[] rawJDDS 		= {0,20};
	public static VendorId_t JDDS = new VendorId_t(rawJDDS);
}
