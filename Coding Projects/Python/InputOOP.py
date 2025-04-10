class ParkingGarage:

    def __init__(self, count):
        self.__count = count
        self.__list = ''

    def park_vehicle(self, license):
        if self.__count == 5:
            self.__list = (license + ', ')
            self.__count -= 1
        elif self.__count > 0 and self.__count < 5:
            self.__list += (license + ', ')
            self.__count -= 1
        else:
            print('Garage is full.')

    def depart_vehicle(self, license):
        if license in self.__list:
            self.__list.strip(license + ', ')
            self.__count += 1
        else:
            print('Vehicle not found.')

    def available_spaces(self):
        print(f'{self.__count} parking spaces are available.')

    def list_vehicles(self):
        print(f'The list of license plates of cars currently parked is: {self.__list}')


def main():
    cont = 'y'

    garage = ParkingGarage(5)

    while cont.lower() == 'y':
        license = input('What is the license of the car to park? ')
        garage.park_vehicle(license)
        cont = input('Are you parking another car? (y/n): ')

    garage.list_vehicles()

    cont = 'y'

    while cont.lower() == 'y':
        todepart = input('What is the license of the departing car? ')
        garage.depart_vehicle(todepart)
        cont = input('Are any other cars departing? (y/n): ')

    garage.available_spaces()

    cont = 'y'

    while cont.lower() == 'y':
        license = input('What is the license of the car to park? ')
        garage.park_vehicle(license)
        cont = input('Are you parking another car? (y/n): ')

    garage.available_spaces()


main()