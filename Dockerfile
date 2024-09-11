FROM node:18.20.4-alpine3.19
WORKDIR /usr/src/app
COPY . ./
RUN yarn install
EXPOSE 3000
RUN yarn build
CMD [ "yarn", "start" ]
