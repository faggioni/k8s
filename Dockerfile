FROM node:18.20.4-alpine3.19
WORKDIR /usr/src/app
COPY package.json ./
COPY yarn.lock ./
RUN yarn install
COPY . .
EXPOSE 8080
RUN yarn build
CMD [ "yarn", "start" ]
