rm -rf ./idlgen/*
echo idlj dds_dcps.idl
idlj  -td ./idlgen ./idl/dds_dcps.idl
echo idlj dds_rtps.idl
idlj  -td ./idlgen -i ./idl ./idl/dds_rtps.idl
echo idlj rtps_types.idl
idlj  -td ./idlgen -i ./idl ./idl/rtps_types.idl
