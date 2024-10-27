import java.io.BufferedReader
import java.io.InputStreamReader

// Function to run ADB commands
fun runCommand(command: String): String {
    val processBuilder = ProcessBuilder(command.split(" "))
    val process = processBuilder.start()
    val reader = BufferedReader(InputStreamReader(process.inputStream))
    val output = StringBuilder()
    var line: String? = reader.readLine()

    while (line != null) {
        output.append(line).append("\n")
        line = reader.readLine()
    }

    return output.toString()
}

// RootRockerDestroyer: Soft factory reset feature
fun rootRockerDestroyer() {
    println("Would you like to perform a soft factory reset? (y/n): ")
    val userInput = readLine()!!

    if (userInput.lowercase() == "y") {
        println("Starting soft factory reset...")
        val resetOutput = runCommand("adb shell am broadcast -a android.intent.action.MASTER_CLEAR")
        println("Soft factory reset completed: \n$resetOutput")
    } else {
        println("Soft factory reset canceled.")
    }
}

fun main() {
    println("###################################################")
    println("### Welcome to RootRocker! 🎸                  ###")
    println("### Let's start rocking!                       ###")
    println("###################################################")

    // Simulated device detection
    val deviceList = runCommand("adb devices")
    println("Connected Devices: \n$deviceList")

    if (deviceList.contains("device")) {
        // Device detected, fetch device info, and root
        fetchDeviceInfo()

        // Engage RootRocker ROCKSTAR defense
        if (!rootRockerRockstar()) {
            println("Rooting process terminated due to security concerns.")
            return
        }

        // Proceed with rooting process
        println("Proceeding with rooting...")

        val pushSu = runCommand("adb push path_to_su_binary /system/xbin/su")
        println("Pushing SU binary: \n$pushSu")

        val chmodSu = runCommand("adb shell chmod 0755 /system/xbin/su")
        println("Setting SU binary permissions: \n$chmodSu")

        // Verify root
        if (verifyRoot()) {
            println("Root access confirmed! The device is now rooted.")
        } else {
            println("Root access failed. Please try again.")
        }

        // Offer the option for a soft factory reset
        rootRockerDestroyer()

    } else {
        println("No device connected. Please connect a Samsung device via USB.")
    }
}
