JOBNAME=example
PBS_ARRAYID=2
TEMP=~/Desktop
dir=examplePolep
SCENARIOPATH=~/Desktop/scenario

java -d64 -server -Xmx3072m -Drun.id=$JOBNAME-$PBS_ARRAYID -Dresults.path=$TEMP/$dir -DSCENARIO_FOLDER=$SCENARIOPATH -Dscenario.file=$SCENARIO -jar $TEMP/$dir/$JARNAME