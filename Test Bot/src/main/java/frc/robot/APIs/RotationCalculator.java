package frc.robot.APIs;

public class RotationCalculator {
    double CPR;
    double maxSpeed;

    public RotationCalculator(double countsPerRotation, double speedMax) {
        CPR = countsPerRotation;
        maxSpeed = speedMax;
    }

    public double calculatePower(double encoderCounts, double targetPostition) {
        double currentPostition = encoderCounts/CPR; 
        double totalDistance = Math.abs(currentPostition - targetPostition);
        boolean forward = true;
        double calcPower = 0;

        if (targetPostition > currentPostition) {
            forward = true;
        } else if (targetPostition < currentPostition) {
            forward = false;
        }

        if (forward) {
            if (totalDistance < 1){

            }
        }

        return calcPower;
    }
}