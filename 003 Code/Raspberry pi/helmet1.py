import paho.mqtt.client as mqtt
import paho.mqtt.publish as publish
import asyncio
import bleak
from bleak import BleakClient

broker_address = "192.168.166.185"
broker_port=1883
client2 = mqtt.Client("ClientPublisher")
client2.connect(broker_address)
print("Connect")

address = "ec:94:cb:5b:bc:3e"
# address2 = "3c:61:05:65:4a:a2"
# address = "ac:67:b2:3c:6e:9a" 
notity_characteristic_uuid1 = "beb5483e-36e1-4688-b7f5-ea07361b26a8"
notity_characteristic_uuid2 = "beb5483e-36e1-4688-b7f5-ea07361b26a9"
# notity_characteristic_uuid3 = "beb5483e-36e1-4688-b7f5-ea07361b26a5"

while True:
	def notify_callback1(sender: int, data: bytearray):
		print('sender: ', sender, 'data: ', data)
		print('\tdata len: ', len(data))
		for idx, b in enumerate(data):
			print('\t\t', idx, ': ', b)
			client2.publish("helmet1/mq7", b)

	def notify_callback2(sender: int, data: bytearray):
		print('sender: ', sender, 'data:', data)
		print('\tdata len: ', len(data))
		for idx, b in enumerate(data):
			print('\t\t', idx, ': ', b)
			client2.publish("helmet1/sw", b)

#	def notify_callback3(sender: int, data: bytearray):
#		print('sender: ', sender, 'data: ', data)
#		print('\tdata len: ', len(data))
#		for idx, b in enumerate(data):
#			print('\t\t', idx, ': ', b)
#			client2.publish("data/cds", b)

	async def run1(address):
		async with BleakClient(address, timeout=5.0) as client:
			print('Helmet1: connected')
			services = await client.get_services()
			for service in services:
				print('service uuid:', service.uuid)
				for characteristic in service.characteristics:
					print('	uuid:', characteristic.uuid)
					print('	handle:', characteristic.handle)
					print('	 properties: ', characteristic.properties)
					if characteristic.uuid == notity_characteristic_uuid1:
						print('가스: connected')
						if 'notify' in characteristic.properties:
							print('try to activate notify.')
							await client.start_notify(characteristic, notify_callback1)
#							client2.publish("vds1/data", await client.start_notify(characteristic, notify_callback))
					if characteristic.uuid == notity_characteristic_uuid2:
						print('조도: connected')
						if 'notify' in characteristic.properties:
							print('try to activate notify.')
							await client.start_notify(characteristic, notify_callback2)
#					if characteristic.uuid == notity_characteristic_uuid3:
#						print('조도: connected')
#						if 'notify' in characteristic.properties:
#							print('try to activate notify.')
#							await client.start_notity(characteristic, notify_callbac3)

			if client.is_connected:
				await asyncio.sleep(30)
				print('try to deactivate notify.')
				await client.stop_notify(notity_characteristic_uuid1)
				await client.stop_notify(notity_characteristic_uuid2)
#				await client.stop_notify(notity_characteristic_uuid3)

		print('disconnect')

	loop = asyncio.get_event_loop()
	loop.run_until_complete(run1(address))
	print('done')

"""
	async def run2(address):
		async with BleakClient(address, timeout=5.0) as client:
			print('충격: connected')
			services = await client.get_services()
			for service in services:
				print('service uuid:', service.uuid)
				for characteristic in service.characteristics:
					print('	uuid:', characteristic.uuid)
					print('	handle:', characteristic.handle)
					print('	properties: ', characteristic.properties)
					if characteristic.uuid == notity_characteristic_uuid2:
						if 'notify' in characteristic.properties:
							print('try to activate notify.')
							await client.start_notify(characteristic, notify_callback2)

			if client.is_connected:
				await asyncio.sleep(1)
				print('try to deactivate notify.')
				await client.stop_notify(notity_characteristic_uuid2)
		print('disconnect')

	loop = asyncio.get_event_loop()
	loop.run_until_complete(run2(address))
	print('done')

	async def run3(address):
		async withBleakClient(address, timeout=5.0) as client:/
			print('조도: connected')
			services = await client.get_services()
			for service in services:
				print('service uuie:', service.uuid)
				for characteristic in service.characteristics:
					print('	uuid:', characteristic.uuid)
					print(' handle:', characteritic.handle)
					print('properties: ', characteristic.properties)
					if characteristic.uuid == notity_characteristic_uuid3:
						if 'notify' in characteristic.properties:
							print('try to activate notify.')
							await client.start_notify(characteristic, notify_callback3)
			if client.is_connected:
				await asyncio.sleep(1)
				print('try to deactivate notify.')
				await client.stop_notify(notity_characteristic_uuid3)
		print('disconnect')

	loop = asyncio.get_event_loop()
	loop.run_until_complete(run3(address))
	print('done')
"""
