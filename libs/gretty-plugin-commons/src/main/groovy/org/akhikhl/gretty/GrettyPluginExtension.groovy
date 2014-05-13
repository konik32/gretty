/*
 * gretty
 *
 * Copyright 2013  Andrey Hihlovskiy.
 *
 * See the file "license.txt" for copying and usage permission.
 */
package org.akhikhl.gretty

import org.gradle.api.GradleException
import ch.qos.logback.classic.Level

class GrettyPluginExtension {

  int port = 8080
  int servicePort = 9900
  int statusPort = 9901
  String contextPath
  Map initParameters = [:]
  String realm
  def realmConfigFile
  def jettyXmlFile
  def jettyEnvXmlFile
  List overlays = []
  List<Closure> onStart = []
  List<Closure> onStop = []
  List<Closure> onScan = []
  List<Closure> onScanFilesChanged = []
  int scanInterval = 0 // scan interval in seconds. When zero, scanning is disabled.
  List scanDirs = [] // list of additional scan directories
  def fastReload = []
  String logbackConfigFile
  String loggingLevel = 'INFO'
  boolean consoleLogEnabled = true
  boolean fileLogEnabled = true
  String logFileName = null
  String logDir = "${System.getProperty('user.home')}/logs"
  String integrationTestTask
  List<String> jvmArgs = []

  void fastReload(String arg) {
    fastReload.add(arg)
  }

  void fastReload(File arg) {
    fastReload.add(arg)
  }

  void fastReload(Map map) {
    fastReload.add(map)
  }

  void initParameter(key, value) {
    initParameters[key] = value
  }

  void overlay(def newValue) {
    if(!(newValue instanceof String))
      throw new GradleException("Overlay ${newValue?.toString()} should be a string")
    overlays.add newValue
  }

  void onScan(Closure newValue) {
    onScan.add newValue
  }

  void onScanFilesChanged(Closure newValue) {
    onScanFilesChanged.add newValue
  }

  void onStart(Closure newValue) {
    onStart.add newValue
  }

  void onStop(Closure newValue) {
    onStop.add newValue
  }

  void scanDir(String value) {
    scanDirs.add(new File(value))
  }

  void scanDir(File value) {
    scanDirs.add(value)
  }

  void scanDir(Object[] args) {
    for(def arg in args)
      if(arg != null)
        scanDirs.add(arg)
  }

  void setFastReload(boolean newValue) {
    fastReload = [ newValue ]
  }
}