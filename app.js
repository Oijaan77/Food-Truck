const { time } = require('console')
const { response } = require('express')
const express = require('express')
const app = express()
const mongoClient = require('mongodb').MongoClient

const url = "mongodb://localhost:27017"

app.use(express.json())

mongoClient.connect(url, (err, db) => {

    if (err) {
        console.log("Error while connecting mongo client")
    } else {

        const myDb = db.db('myDb')
        const collection = myDb.collection('userDb3')
        const collection2 = myDb.collection('orderDb3')

        app.post('/signup', (req, res) => {

            const newUser = {
                email: req.body.email,
                password: req.body.password
            }

            const query = { email: newUser.email }

            collection.findOne(query, (err, result) => {

                if (result == null) {
                    collection.insertOne(newUser, (err, result) => {
                        res.status(200).send()
                    })
                } else {
                    res.status(400).send()
                }

            })

        })
        app.post('/order', (req, res) => {

            const newOrder = {
                id: req.body.id,
                email: req.body.email,
                items: req.body.items,
                truck: req.body.truck,
                time: req.body.time,
                cost: req.body.cost,
                complete: req.body.complete
            }

            const query = { id: newOrder.id }

            collection2.findOne(query, (err, result) => {

                if (result == null) {
                    collection2.insertOne(newOrder, (err, result) => {
                        res.status(200).send()
                    })
                } else {
                    res.status(400).send()
                }

            })

        });
        app.put('/orderUp', (req, res) => {

            const updateOrder = {
                id: req.body.id,
                email: req.body.email,
                items: req.body.items,
                truck: req.body.truck,
                time: req.body.time,
                cost: req.body.cost,
                complete: req.body.complete
            }

            const query = { id: updateOrder.id }

            collection2.findOneAndReplace(query, updateOrder, (err, result) => {
                if(result != null) {
                    res.status(200)
                }
            });
        });

        app.get('/', function (req, res) {
            collection2.find({}).toArray(function(err, result){
               console.log(result)
               res.send(result)
            });
        });

        app.get('/orders', function (req, res) {
            const check = {
                email: req.query.email
            }
            console.log(check)
            collection2.find(check).toArray(function(err, result){
               console.log(result)
               res.send(result)
            });
        });

        app.post('/login', (req, res) => {

            const query = {
                email: req.body.email, 
                password: req.body.password
            }

            collection.findOne(query, (err, result) => {

                if (result != null) {

                    const objToSend = {
                        email: result.email
                    }

                    res.status(200).send(JSON.stringify(objToSend))

                } else {
                    res.status(404).send()
                }

            })

        });
    }

})
app.listen(3000, () => {
    console.log("Listening on port 3000...")
})
