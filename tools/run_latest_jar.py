import os 
import argparse
import sys
from stat import *

# I'm gonna keep this simple because I'm lazy
default_directory = '../jars'

class Jars:
    #The command to be executed
    __cmd__ = 'java -jar'
    
    def __init__(self,directory):
        self.directory = directory
        self.find_latest()
        self.run_jar()
    
    def find_latest(self):
        self.latest_file = ''
        for file in os.listdir(self.directory):
            file = self.directory + '/' + file
            if(self.latest_file == ''):
                self.latest_file = file
            else:
                if(os.stat(file)[ST_CTIME] > os.stat(self.latest_file)[ST_CTIME]):
                    self.latest_file = file
                
        
    def run_jar(self):
        print self.__cmd__,' ',self.latest_file
        os.system(self.__cmd__ + ' ' + self.latest_file)
        


if __name__ == '__main__':
    parser = argparse.ArgumentParser(description='Runs the latest jar file found in directory')
    
    parser.add_argument('dir',nargs='?', default=default_directory)
    p = parser.parse_args()
    print p
    jars = Jars(p.dir)
    