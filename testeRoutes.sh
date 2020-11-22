#!/bin/bash

echo "Testando Gender Routes"
echo "expect 201 Created"
curl -XPOST -H "Content-type: application/json" -d '{"label":"viadinho"}' localhost:8080/gender -v

echo "expect 409 Conflic"
curl -XPOST -H "Content-type: application/json" -d '{"label":"viadinho"}' localhost:8080/gender -v

echo "expect 400 BadRequest"
curl -XPOST -H "Content-type: application/json" -d '""' localhost:8080/gender -v
echo "expect 400 BadRequest"
curl -XPOST -H "Content-type: application/json" -d '{}' localhost:8080/gender -v
echo "expect 400 BadRequest"
curl -XPOST -H "Content-type: application/json" -d '{"campoErradoNãoExisteNaClasse":"viadinho"}' localhost:8080/gender -v

echo "expected 200 OK"


echo "expect 200 OK"
curl -XDELETE -H "Content-type: application/json" -d '{"label":"viadinho"}' localhost:8080/gender/e8a9d2d9-8509-3e07-b8a2-004921550b67 -v


