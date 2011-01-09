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

//OCT 2010 http://www.omgwiki.org/dds/content/page/dds-rtps-vendor-ids
//#define VENDORID_RTI {1,1}
//#define VENDORID_PRISMTECH {1,2}
//#define VENDORID_OCI {1,3}
//#define VENDORID_MILSOFT {1,4}
//#define VENDORID_KONGSBERG {1,5}
//#define VENDORID_TWINOAKS {1,6}
//#define VENDORID_LAKOTA {1,7}
//#define VENDORID_ICOUP {1,8}
//need to apply to OMG for a proper Vendor ID
//#define VENDORID_JDDS {1,20}

public interface VENDORID {
	public static byte[] rawRTI 		= {0,1};
	public static VendorId_t RTI = new VendorId_t(rawRTI);
	
	public static byte[] rawPrismTech 	= {0,2};
	public static VendorId_t PrismTech = new VendorId_t(rawPrismTech);
	
	public static byte[] rawOCI 		= {0,3};
	public static VendorId_t OCI = new VendorId_t(rawOCI);
	
	public static byte[] rawMilSoft 	= {0,4};
	public static VendorId_t MilSoft = new VendorId_t(rawMilSoft);
	
	public static byte[] rawKongsberg 	= {0,5};
	public static VendorId_t Kongsberg = new VendorId_t(rawKongsberg);
	
	public static byte[] rawTwinOaks 	= {0,6};
	public static VendorId_t TwinOaks = new VendorId_t(rawTwinOaks);
	
	public static byte[] rawLakota 		= {0,7};
	public static VendorId_t Lakota = new VendorId_t(rawLakota);
	
	public static byte[] rawIcoup 		= {0,8};
	public static VendorId_t Icoup = new VendorId_t(rawIcoup);
	
	public static byte[] rawJDDS 		= {0,20};
	public static VendorId_t JDDS = new VendorId_t(rawJDDS);
}
